package com.marijamihajlovska.gymtraininggenerator.data

import com.marijamihajlovska.gymtraininggenerator.model.Difficulty
import com.marijamihajlovska.gymtraininggenerator.model.ExerciseCategory
import com.marijamihajlovska.gymtraininggenerator.model.ExerciseItem

object ExerciseRepository {

    private fun ytEmbed(id: String) = "https://www.youtube.com/embed/$id"
    private fun ytThumb(id: String) = "https://img.youtube.com/vi/$id/hqdefault.jpg"

    val all: List<ExerciseItem> = listOf(

        // ── CHEST ─────────────────────────────────────────────────────────────
        ExerciseItem(
            id = "bench_press",
            name = "Bench Press",
            category = ExerciseCategory.CHEST,
            muscles = "Chest, Triceps, Shoulders",
            equipment = "Barbell, Bench",
            difficulty = Difficulty.INTERMEDIATE,
            sets = "4", reps = "8–12", restSeconds = 90,
            description = "Lie on a flat bench with the barbell racked above your chest. Grip just wider than shoulder-width, lower the bar to mid-chest under control, then press back up powerfully.",
            imageUrl = "https://sportivetricksstorage.blob.core.windows.net/images/articles/training/technique/bench-press-dumbell/01-dumbellbenchpress.webp",
            videoUrl = ytEmbed("SCVCLChPQFY")
        ),
        ExerciseItem(
            id = "incline_db_press",
            name = "Incline Dumbbell Press",
            category = ExerciseCategory.CHEST,
            muscles = "Upper Chest, Shoulders",
            equipment = "Dumbbells, Incline Bench",
            difficulty = Difficulty.INTERMEDIATE,
            sets = "3", reps = "10–12", restSeconds = 75,
            description = "Set bench to 30–45°. Press dumbbells from chest level overhead keeping elbows at 75°. Squeeze upper chest at the top.",
            imageUrl = "https://liftmanual.com/wp-content/uploads/2023/04/dumbbell-incline-hammer-press.jpg" ,
            videoUrl = ytEmbed("hChjZQhX1Ls")
        ),
        ExerciseItem(
            id = "push_ups",
            name = "Push-ups",
            category = ExerciseCategory.CHEST,
            muscles = "Chest, Triceps, Core",
            equipment = "Bodyweight",
            difficulty = Difficulty.BEGINNER,
            sets = "3", reps = "15–20", restSeconds = 45,
            description = "Start in high plank with hands shoulder-width apart. Lower chest toward floor keeping elbows at 45°. Push back up to full extension.",
            imageUrl = "https://weighttraining.guide/wp-content/uploads/2016/10/push-up-tall-resized.png",
            videoUrl = ytEmbed("lsRAK6cr5kY")
        ),
        ExerciseItem(
            id = "cable_fly",
            name = "Cable Fly",
            category = ExerciseCategory.CHEST,
            muscles = "Chest",
            equipment = "Cable Machine",
            difficulty = Difficulty.INTERMEDIATE,
            sets = "3", reps = "12–15", restSeconds = 60,
            description = "Stand between cable towers with handles at chest height. With slight elbow bend, bring handles together in an arc in front of your chest.",
            imageUrl = "https://liftmanual.com/wp-content/uploads/2023/04/cable-standing-fly.jpg",
            videoUrl = ytEmbed("8Um35Es-ROE")
        ),
        ExerciseItem(
            id = "dips",
            name = "Dips",
            category = ExerciseCategory.CHEST,
            muscles = "Lower Chest, Triceps",
            equipment = "Dip Bars",
            difficulty = Difficulty.INTERMEDIATE,
            sets = "3", reps = "10–15", restSeconds = 60,
            description = "Grip parallel bars and lower your body by bending elbows to 90°. Lean forward slightly to target the chest. Press back to the start.",
            imageUrl = "https://liftmanual.com/wp-content/uploads/2023/04/impossible-dips.jpg",
            videoUrl = ytEmbed("85u_8mz5lBA")
        ),

        // ── BACK ──────────────────────────────────────────────────────────────
        ExerciseItem(
            id = "deadlift",
            name = "Deadlift",
            category = ExerciseCategory.BACK,
            muscles = "Lower Back, Glutes, Hamstrings",
            equipment = "Barbell",
            difficulty = Difficulty.ADVANCED,
            sets = "4", reps = "5–8", restSeconds = 120,
            description = "Stand over barbell, feet hip-width. Hinge at hips, grip the bar just outside knees. Drive through heels, extend hips and knees simultaneously to stand tall.",
            imageUrl = "https://cdn.shopify.com/s/files/1/0072/7754/3493/files/muscles-deadlifts-visual.jpg?v=1771888083",
            videoUrl = ytEmbed("1ZXobu7JvvE")
        ),
        ExerciseItem(
            id = "pull_ups",
            name = "Pull-ups",
            category = ExerciseCategory.BACK,
            muscles = "Lats, Biceps, Rear Delts",
            equipment = "Pull-up Bar",
            difficulty = Difficulty.INTERMEDIATE,
            sets = "3", reps = "6–12", restSeconds = 90,
            description = "Hang from bar with overhand grip slightly wider than shoulders. Pull your chin above the bar by driving elbows toward hips. Lower slowly.",
            imageUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTVp1Yg76cFm42EWGlYWUbogVoxYXBJzDkmEw&s",
            videoUrl = ytEmbed("p40iUjf02j0")
        ),
        ExerciseItem(
            id = "lat_pulldown",
            name = "Lat Pulldown",
            category = ExerciseCategory.BACK,
            muscles = "Lats, Biceps",
            equipment = "Cable Machine",
            difficulty = Difficulty.BEGINNER,
            sets = "3", reps = "10–12", restSeconds = 60,
            description = "Sit at lat pulldown station. Grip bar wider than shoulders. Pull bar down to upper chest while leaning slightly back and squeezing shoulder blades.",
            imageUrl ="https://s3.amazonaws.com/prod.skimble/assets/2877560/image_iphone.jpg" ,
            videoUrl = ytEmbed("CAwf7n6Luuc")
        ),
        ExerciseItem(
            id = "barbell_rows",
            name = "Barbell Rows",
            category = ExerciseCategory.BACK,
            muscles = "Mid Back, Lats, Biceps",
            equipment = "Barbell",
            difficulty = Difficulty.INTERMEDIATE,
            sets = "4", reps = "8–10", restSeconds = 90,
            description = "Hinge at hips until torso is near parallel to floor. Pull barbell to lower chest, leading with elbows. Keep back flat throughout.",
            imageUrl = "https://weighttraining.guide/wp-content/uploads/2016/10/Bent-over-barbell-row.png",
            videoUrl = ytEmbed("9efgcAjQe7E")
        ),
        ExerciseItem(
            id = "cable_rows",
            name = "Cable Rows",
            category = ExerciseCategory.BACK,
            muscles = "Mid Back, Lats",
            equipment = "Cable Machine",
            difficulty = Difficulty.BEGINNER,
            sets = "3", reps = "12–15", restSeconds = 60,
            description = "Sit at cable row machine. Pull handle to lower abdomen while sitting tall. Squeeze shoulder blades together at peak contraction.",
            imageUrl = "https://weighttraining.guide/wp-content/uploads/2018/11/Standing-cable-row-resized.png",
            videoUrl = ytEmbed("7o2oolbmzeI")
        ),
        ExerciseItem(
            id = "face_pulls",
            name = "Face Pulls",
            category = ExerciseCategory.BACK,
            muscles = "Rear Delts, Rotator Cuff",
            equipment = "Cable Machine",
            difficulty = Difficulty.BEGINNER,
            sets = "3", reps = "15–20", restSeconds = 45,
            description = "Attach rope at face height. Pull rope toward face with elbows high and wide, finishing with hands beside ears. Great for shoulder health.",
            imageUrl = "https://liftmanual.com/wp-content/uploads/2023/04/cable-standing-face-pull.jpg",
            videoUrl = ytEmbed("rep-qVOkqgk")
        ),

        // ── SHOULDERS ─────────────────────────────────────────────────────────
        ExerciseItem(
            id = "overhead_press",
            name = "Overhead Press",
            category = ExerciseCategory.SHOULDERS,
            muscles = "Shoulders, Triceps, Core",
            equipment = "Barbell",
            difficulty = Difficulty.INTERMEDIATE,
            sets = "4", reps = "8–10", restSeconds = 90,
            description = "Stand with barbell at upper chest, grip just outside shoulders. Press overhead until arms are fully extended. Lower under control.",
            imageUrl = "https://weighttraining.guide/wp-content/uploads/2022/04/Standing-wide-grip-barbell-overhead-press.png",
            videoUrl = ytEmbed("2yjwXTZQDDI")
        ),
        ExerciseItem(
            id = "lateral_raises",
            name = "Lateral Raises",
            category = ExerciseCategory.SHOULDERS,
            muscles = "Lateral Delts",
            equipment = "Dumbbells",
            difficulty = Difficulty.BEGINNER,
            sets = "3", reps = "15–20", restSeconds = 45,
            description = "Hold dumbbells at sides with slight elbow bend. Raise arms out to sides until parallel with floor. Control the lowering phase.",
            imageUrl = "https://weighttraining.guide/wp-content/uploads/2016/05/dumbbell-lateral-raise-resized.png",
            videoUrl = ytEmbed("3VcKaXpzqRo")
        ),
        ExerciseItem(
            id = "front_raises",
            name = "Front Raises",
            category = ExerciseCategory.SHOULDERS,
            muscles = "Front Delts",
            equipment = "Dumbbells",
            difficulty = Difficulty.BEGINNER,
            sets = "3", reps = "12–15", restSeconds = 45,
            description = "Hold dumbbells in front of thighs. Raise arms forward to shoulder height, keeping a slight elbow bend. Lower under control.",
            imageUrl = "https://weighttraining.guide/wp-content/uploads/2016/10/Dumbbell-Standing-Alternate-Front-Raise-resized.png",
            videoUrl = ytEmbed("t7fuZ0KhDA")
        ),
        ExerciseItem(
            id = "rear_delt_fly",
            name = "Rear Delt Fly",
            category = ExerciseCategory.SHOULDERS,
            muscles = "Rear Delts",
            equipment = "Dumbbells",
            difficulty = Difficulty.BEGINNER,
            sets = "3", reps = "15–20", restSeconds = 45,
            description = "Hinge forward at hips. With dumbbells hanging, raise arms out to sides squeezing rear delts. Keep a slight bend in the elbows.",
            imageUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQqXlSvdeC2wBAwVUr97JZs8eqI7HpOfViHDw&s",
            videoUrl = ytEmbed("nlkF7_2O_Lw")
        ),
        ExerciseItem(
            id = "arnold_press",
            name = "Arnold Press",
            category = ExerciseCategory.SHOULDERS,
            muscles = "All Delt Heads, Triceps",
            equipment = "Dumbbells",
            difficulty = Difficulty.INTERMEDIATE,
            sets = "3", reps = "10–12", restSeconds = 60,
            description = "Start with dumbbells at chin level, palms facing you. As you press overhead, rotate palms forward. Reverse on the way down.",
            imageUrl = "https://weighttraining.guide/wp-content/uploads/2016/10/Arnold-press-resized.png",
            videoUrl = ytEmbed("mXRhpXwW-gs")
        ),

        // ── ARMS ──────────────────────────────────────────────────────────────
        ExerciseItem(
            id = "bicep_curls",
            name = "Bicep Curls",
            category = ExerciseCategory.ARMS,
            muscles = "Biceps",
            equipment = "Dumbbells",
            difficulty = Difficulty.BEGINNER,
            sets = "3", reps = "12–15", restSeconds = 45,
            description = "Hold dumbbells with palms forward. Keep elbows pinned to sides. Curl weights to shoulder level squeezing biceps. Lower slowly.",
            imageUrl = "https://weighttraining.guide/wp-content/uploads/2016/05/Dumbbell-Alternate-Biceps-Curl-resized.png",
            videoUrl = ytEmbed("sAq_ocpRh_I")
        ),
        ExerciseItem(
            id = "hammer_curls",
            name = "Hammer Curls",
            category = ExerciseCategory.ARMS,
            muscles = "Biceps, Brachialis",
            equipment = "Dumbbells",
            difficulty = Difficulty.BEGINNER,
            sets = "3", reps = "12–15", restSeconds = 45,
            description = "Hold dumbbells with palms facing each other (neutral grip). Curl to shoulder height while keeping wrists neutral. Great for overall arm thickness.",
            imageUrl = "https://cdn.shopify.com/s/files/1/1876/4703/files/shutterstock_419477203_480x480.jpg?v=1636560233",
            videoUrl = ytEmbed("zC3nLlEvin4")
        ),
        ExerciseItem(
            id = "concentration_curls",
            name = "Concentration Curls",
            category = ExerciseCategory.ARMS,
            muscles = "Biceps",
            equipment = "Dumbbell",
            difficulty = Difficulty.BEGINNER,
            sets = "3", reps = "12 each side", restSeconds = 45,
            description = "Sit on a bench and brace your elbow against your inner thigh. Curl the dumbbell to your shoulder focusing on the peak contraction.",
            imageUrl = "https://weighttraining.guide/wp-content/uploads/2016/05/Dumbbell-Concentration-Curl-resized.png",
            videoUrl = ytEmbed("Jvj2wV0vOYU")
        ),
        ExerciseItem(
            id = "tricep_dips",
            name = "Tricep Dips",
            category = ExerciseCategory.ARMS,
            muscles = "Triceps",
            equipment = "Dip Bars / Bench",
            difficulty = Difficulty.BEGINNER,
            sets = "3", reps = "12–15", restSeconds = 45,
            description = "Place hands on a bench behind you, legs extended. Lower your body by bending elbows to 90°, then press back up. Keep torso upright.",
            imageUrl = "https://cdn.shopify.com/s/files/1/1497/9682/files/Benefits_of_Mastering_Tricep_Dips.jpg?v=1687254157&width=750",
            videoUrl = ytEmbed("aCa7cc8ECp8")
        ),
        ExerciseItem(
            id = "skull_crushers",
            name = "Skull Crushers",
            category = ExerciseCategory.ARMS,
            muscles = "Triceps",
            equipment = "Barbell / EZ Bar",
            difficulty = Difficulty.INTERMEDIATE,
            sets = "3", reps = "10–12", restSeconds = 60,
            description = "Lie on bench with bar extended above chest. Keeping upper arms vertical, bend elbows to lower bar toward forehead. Extend back up.",
            imageUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTWKusz70M1CDNQOL-aoNKrWGXM_SjCWIxDRg&s",
            videoUrl = ytEmbed("d_KZxkY_0cM")
        ),
        ExerciseItem(
            id = "tricep_pushdown",
            name = "Tricep Pushdown",
            category = ExerciseCategory.ARMS,
            muscles = "Triceps",
            equipment = "Cable Machine",
            difficulty = Difficulty.BEGINNER,
            sets = "3", reps = "12–15", restSeconds = 45,
            description = "Stand at cable machine with bar at face height. Keep elbows at sides and push bar down to hip level. Squeeze triceps at full extension.",
            imageUrl = "https://trainingstation.co.uk/cdn/shop/articles/Tricep-pushdown-movement_ddb8dbd8-566d-4f55-99e0-36c35790234a_1224x.png?v=1739005533",
            videoUrl = ytEmbed("2-LAMcpzODU")
        ),

        // ── LEGS ──────────────────────────────────────────────────────────────
        ExerciseItem(
            id = "barbell_squat",
            name = "Barbell Squat",
            category = ExerciseCategory.LEGS,
            muscles = "Quads, Glutes, Hamstrings",
            equipment = "Barbell",
            difficulty = Difficulty.INTERMEDIATE,
            sets = "4", reps = "8–12", restSeconds = 120,
            description = "Bar on upper traps, feet shoulder-width. Brace core, hinge at hips and knees to squat until thighs are parallel. Drive through heels to stand.",
            imageUrl = "https://weighttraining.guide/wp-content/uploads/2016/10/barbell-squat-resized-FIXED-2.png",
            videoUrl = ytEmbed("SW_C1A-rejs")
        ),
        ExerciseItem(
            id = "romanian_deadlift",
            name = "Romanian Deadlift",
            category = ExerciseCategory.LEGS,
            muscles = "Hamstrings, Glutes",
            equipment = "Barbell",
            difficulty = Difficulty.INTERMEDIATE,
            sets = "3", reps = "10–12", restSeconds = 90,
            description = "Hold bar at hip height. Hinge forward at hips with slight knee bend, lowering bar along legs until you feel hamstring stretch. Drive hips forward to stand.",
            imageUrl = "https://weighttraining.guide/wp-content/uploads/2016/10/Barbell-Romanian-Deadlift.png",
            videoUrl = ytEmbed("JCXUYuzwNrM")
        ),
        ExerciseItem(
            id = "leg_press",
            name = "Leg Press",
            category = ExerciseCategory.LEGS,
            muscles = "Quads, Glutes",
            equipment = "Leg Press Machine",
            difficulty = Difficulty.BEGINNER,
            sets = "4", reps = "12–15", restSeconds = 90,
            description = "Sit in machine with feet shoulder-width on platform. Lower platform until knees reach 90°. Push through heels to full extension without locking out knees.",
            imageUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSUGerA_gDLECAtSVzQnY98rSVV4fvLV8f9yA&s",
            videoUrl = ytEmbed("IZxyjW7MPJQ")
        ),
        ExerciseItem(
            id = "lunges",
            name = "Lunges",
            category = ExerciseCategory.LEGS,
            muscles = "Quads, Glutes, Hamstrings",
            equipment = "Bodyweight / Dumbbells",
            difficulty = Difficulty.BEGINNER,
            sets = "3", reps = "12 each leg", restSeconds = 60,
            description = "Stand tall, step forward with one leg and lower back knee toward the floor. Push through front heel to return. Alternate legs.",
            imageUrl = "https://trainingstation.co.uk/cdn/shop/articles/Lunges-movment_d958998d-2a9f-430e-bdea-06f1e2bcc835_900x.webp?v=1741687877",
            videoUrl = ytEmbed("D7KaRcUTQeE")
        ),
        ExerciseItem(
            id = "calf_raises",
            name = "Calf Raises",
            category = ExerciseCategory.LEGS,
            muscles = "Calves (Gastrocnemius)",
            equipment = "Bodyweight / Machine",
            difficulty = Difficulty.BEGINNER,
            sets = "4", reps = "15–20", restSeconds = 45,
            description = "Stand with balls of feet on an elevated surface. Rise as high as possible onto toes. Lower slowly for a full stretch at the bottom.",
            imageUrl = "https://weighttraining.guide/wp-content/uploads/2016/10/Lever-Standing-Calf-Raise-resized.png",
            videoUrl = ytEmbed("g_E7_q1z2bo")
        ),
        ExerciseItem(
            id = "glute_bridges",
            name = "Glute Bridges",
            category = ExerciseCategory.LEGS,
            muscles = "Glutes, Hamstrings",
            equipment = "Bodyweight / Barbell",
            difficulty = Difficulty.BEGINNER,
            sets = "3", reps = "15–20", restSeconds = 45,
            description = "Lie on back with knees bent and feet flat. Drive hips up by squeezing glutes, forming a straight line from knees to shoulders. Hold 2 seconds at top.",
            imageUrl = "https://liftmanual.com/wp-content/uploads/2023/04/barbell-glute-bridge.jpg",
            videoUrl = ytEmbed("ylpfCk3i-0Y")
        ),
        ExerciseItem(
            id = "leg_curl",
            name = "Leg Curl",
            category = ExerciseCategory.LEGS,
            muscles = "Hamstrings",
            equipment = "Leg Curl Machine",
            difficulty = Difficulty.BEGINNER,
            sets = "3", reps = "12–15", restSeconds = 60,
            description = "Lie face-down on the machine with pads on back of ankles. Curl heels toward glutes squeezing hamstrings at peak. Lower under control.",
            imageUrl = "https://fitnessvolt.com/wp-content/uploads/2019/04/seated-leg-curl.jpg.webp",
            videoUrl = ytEmbed("ELOCsoDSmrg")
        ),

        // ── CORE ──────────────────────────────────────────────────────────────
        ExerciseItem(
            id = "plank",
            name = "Plank",
            category = ExerciseCategory.CORE,
            muscles = "Core, Transverse Abdominis",
            equipment = "Bodyweight",
            difficulty = Difficulty.BEGINNER,
            sets = "3", reps = "30–60 sec", restSeconds = 45,
            description = "Hold a push-up position (or on forearms). Keep body in a straight line from head to heels. Breathe steadily and brace your core.",
            imageUrl = "https://liftmanual.com/wp-content/uploads/2023/04/front-plank.jpg",
            videoUrl = ytEmbed("pSHjTRCQxIw")
        ),
        ExerciseItem(
            id = "crunches",
            name = "Crunches",
            category = ExerciseCategory.CORE,
            muscles = "Abs",
            equipment = "Bodyweight",
            difficulty = Difficulty.BEGINNER,
            sets = "3", reps = "20–25", restSeconds = 30,
            description = "Lie on back with knees bent. Place hands behind head. Curl shoulders off floor by contracting abs. Do not pull on neck.",
            imageUrl = "https://i0.wp.com/muscu-street-et-crossfit.fr/wp-content/uploads/2021/10/Muscles-Crunch.001.jpeg?resize=1024%2C576&ssl=1",
            videoUrl = ytEmbed("NGRKFMKhF8s")
        ),
        ExerciseItem(
            id = "russian_twists",
            name = "Russian Twists",
            category = ExerciseCategory.CORE,
            muscles = "Obliques, Abs",
            equipment = "Bodyweight / Weight Plate",
            difficulty = Difficulty.BEGINNER,
            sets = "3", reps = "20 total", restSeconds = 30,
            description = "Sit with knees bent, lean back slightly. Rotate torso side to side, tapping the floor beside each hip. Add a weight to increase difficulty.",
            imageUrl = "https://cdn.shopify.com/s/files/1/0015/6837/4819/files/Decline_Russian_twist_for_added_challenge_480x480.jpg?v=1759914966",
            videoUrl = ytEmbed("wkD8rjkodUI")
        ),
        ExerciseItem(
            id = "leg_raises",
            name = "Leg Raises",
            category = ExerciseCategory.CORE,
            muscles = "Lower Abs, Hip Flexors",
            equipment = "Bodyweight",
            difficulty = Difficulty.INTERMEDIATE,
            sets = "3", reps = "15–20", restSeconds = 45,
            description = "Lie flat on back with legs straight. Keep lower back pressed to floor. Raise legs to 90°, then lower slowly without touching the floor.",
            imageUrl = "https://liftmanual.com/wp-content/uploads/2023/04/lying-leg-raise.jpg",
            videoUrl = ytEmbed("l4kQd9eWclE")
        ),
        ExerciseItem(
            id = "mountain_climbers",
            name = "Mountain Climbers",
            category = ExerciseCategory.CORE,
            muscles = "Core, Hip Flexors",
            equipment = "Bodyweight",
            difficulty = Difficulty.BEGINNER,
            sets = "3", reps = "20–30 total", restSeconds = 45,
            description = "Start in high plank. Alternate driving knees toward chest as quickly as possible while keeping hips level and core braced.",
            imageUrl = "https://i.pinimg.com/736x/d0/29/df/d029df978db1dd6129c259aae841558d.jpg",
            videoUrl = ytEmbed("UOGvtqv856A")
        ),
        ExerciseItem(
            id = "ab_rollout",
            name = "Ab Rollout",
            category = ExerciseCategory.CORE,
            muscles = "Core, Lats",
            equipment = "Ab Wheel",
            difficulty = Difficulty.ADVANCED,
            sets = "3", reps = "8–12", restSeconds = 60,
            description = "Kneel on floor holding ab wheel. Roll forward extending body, keeping hips from sagging. Use core to pull back to the starting position.",
            imageUrl = "data:image/jpeg;base64,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",
            videoUrl = ytEmbed("zCsW9L2qi-0")
        ),

        // ── CARDIO ────────────────────────────────────────────────────────────
        ExerciseItem(
            id = "jumping_jacks",
            name = "Jumping Jacks",
            category = ExerciseCategory.CARDIO,
            muscles = "Full Body",
            equipment = "Bodyweight",
            difficulty = Difficulty.BEGINNER,
            sets = "3", reps = "30–60 sec", restSeconds = 30,
            description = "Stand with feet together, arms at sides. Jump feet out while raising arms overhead. Return to start and repeat. Keep a steady rhythm.",
            imageUrl = "https://liftmanual.com/wp-content/uploads/2023/04/jumping-jack.jpg",
            videoUrl = ytEmbed("CWpmIW6l-YA")
        ),
        ExerciseItem(
            id = "burpees",
            name = "Burpees",
            category = ExerciseCategory.CARDIO,
            muscles = "Full Body",
            equipment = "Bodyweight",
            difficulty = Difficulty.INTERMEDIATE,
            sets = "3", reps = "10–15", restSeconds = 60,
            description = "Drop to push-up position, do one push-up, jump feet to hands, then explode up with arms overhead. One of the most effective fat-burning movements.",
            imageUrl = "https://cdn.shopify.com/s/files/1/0701/9950/9207/files/atl_20200407190441_149_480x480.jpg?v=1722398712",
            videoUrl = ytEmbed("auBLPXO8Fww")
        ),
        ExerciseItem(
            id = "high_knees",
            name = "High Knees",
            category = ExerciseCategory.CARDIO,
            muscles = "Hip Flexors, Core",
            equipment = "Bodyweight",
            difficulty = Difficulty.BEGINNER,
            sets = "3", reps = "30 sec", restSeconds = 30,
            description = "Run in place driving knees up to hip height with each step. Pump arms and maintain an upright posture. Keep a fast pace.",
            imageUrl = "https://liftmanual.com/wp-content/uploads/2023/04/high-knee-run.jpg",
            videoUrl = ytEmbed("ZNDHivUg7vA")
        ),
        ExerciseItem(
            id = "box_jumps",
            name = "Box Jumps",
            category = ExerciseCategory.CARDIO,
            muscles = "Quads, Glutes, Calves",
            equipment = "Plyo Box",
            difficulty = Difficulty.INTERMEDIATE,
            sets = "4", reps = "8–10", restSeconds = 60,
            description = "Stand in front of a sturdy box. Dip slightly and jump onto the box with both feet. Step back down and reset. Focus on soft landings.",
            imageUrl = "https://liftmanual.com/wp-content/uploads/2023/04/jump-box.jpg",
            videoUrl = ytEmbed("NBY9-kTuHEk")
        ),
        ExerciseItem(
            id = "jump_rope",
            name = "Jump Rope",
            category = ExerciseCategory.CARDIO,
            muscles = "Calves, Shoulders, Core",
            equipment = "Jump Rope",
            difficulty = Difficulty.BEGINNER,
            sets = "3", reps = "60 sec", restSeconds = 30,
            description = "Hold rope handles at hip height. Jump with both feet over the rope as it passes under. Stay light on your feet and maintain a steady tempo.",
            imageUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT2_dljxKAnFytUXSRZSai0ALQmpB3c6NIt6A&s",
            videoUrl = ytEmbed("u3zgHI8QnqE")
        ),

        // ── FULL BODY ─────────────────────────────────────────────────────────
        ExerciseItem(
            id = "kettlebell_swing",
            name = "Kettlebell Swing",
            category = ExerciseCategory.FULL_BODY,
            muscles = "Glutes, Hamstrings, Core, Shoulders",
            equipment = "Kettlebell",
            difficulty = Difficulty.INTERMEDIATE,
            sets = "4", reps = "15–20", restSeconds = 60,
            description = "Hinge at hips and swing the kettlebell between legs. Explosively drive hips forward to swing it to shoulder height. The power comes from the hips, not the arms.",
            imageUrl = "https://weighttraining.guide/wp-content/uploads/2017/03/Kettlebell-Swing-resized.png",
            videoUrl = ytEmbed("mKDIuUbH94Q")
        ),
        ExerciseItem(
            id = "clean_and_press",
            name = "Clean and Press",
            category = ExerciseCategory.FULL_BODY,
            muscles = "Full Body",
            equipment = "Barbell / Dumbbells",
            difficulty = Difficulty.ADVANCED,
            sets = "4", reps = "5–8", restSeconds = 120,
            description = "Pull barbell from floor explosively to shoulder height (clean), then press overhead. A complete power movement targeting every major muscle group.",
            imageUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQmun9w5WfwoSDoBbJBy825igv6716HxcuNKg&s",
            videoUrl = ytEmbed("KCe8l86-alA")
        ),
        ExerciseItem(
            id = "thruster",
            name = "Thruster",
            category = ExerciseCategory.FULL_BODY,
            muscles = "Quads, Shoulders, Core",
            equipment = "Barbell / Dumbbells",
            difficulty = Difficulty.ADVANCED,
            sets = "3", reps = "10–12", restSeconds = 90,
            description = "Hold bar at chest. Squat to parallel, then use the momentum of standing to drive the bar overhead in one fluid motion. A core CrossFit movement.",
            imageUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR7kRYcrudKOZRGHzvl4P0XMBEivM32bL2_vQ&s",
        videoUrl = ytEmbed("L219ltL15zk")
        )
    )

    fun getByCategory(category: ExerciseCategory) = all.filter { it.category == category }

    fun search(query: String) = all.filter {
        it.name.contains(query, ignoreCase = true) ||
        it.muscles.contains(query, ignoreCase = true) ||
        it.equipment.contains(query, ignoreCase = true)
    }
}
