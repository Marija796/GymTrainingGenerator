import * as functions from "firebase-functions";
import * as admin from "firebase-admin";

admin.initializeApp();
const db = admin.firestore();
const messaging = admin.messaging();

// Triggered when a new workout plan is saved to Firestore.
// Sends a push notification to the user's device.
export const onWorkoutCreated = functions.firestore
  .document("workouts/{workoutId}")
  .onCreate(async (snap, context) => {
    const workout = snap.data();
    const userId = workout?.userId as string;
    if (!userId) return null;

    // Fetch the user's FCM token stored in Firestore
    const userDoc = await db.collection("users").doc(userId).get();
    const fcmToken = userDoc.data()?.fcmToken as string;
    if (!fcmToken) return null;

    const goal = workout?.goal ?? "your goal";
    const message = {
      token: fcmToken,
      notification: {
        title: "New Workout Plan Ready! 💪",
        body: `Your ${goal} plan has been generated. Time to train!`,
      },
      android: {
        notification: {
          channelId: "workout_notifications",
          priority: "high" as const,
        },
      },
    };

    try {
      await messaging.send(message);
      console.log(`Notification sent to user ${userId}`);
    } catch (err) {
      console.error("Error sending notification:", err);
    }
    return null;
  });

// Runs every day at midnight — deletes workout documents older than 90 days.
export const cleanOldWorkouts = functions.pubsub
  .schedule("every 24 hours")
  .onRun(async () => {
    const cutoff = new Date();
    cutoff.setDate(cutoff.getDate() - 90);

    const snapshot = await db
      .collection("workouts")
      .where("createdAt", "<", cutoff)
      .get();

    const batch = db.batch();
    snapshot.docs.forEach((doc) => batch.delete(doc.ref));
    await batch.commit();

    console.log(`Deleted ${snapshot.size} workouts older than 90 days`);
    return null;
  });
