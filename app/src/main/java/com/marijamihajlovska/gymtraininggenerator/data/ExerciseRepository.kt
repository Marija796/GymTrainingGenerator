package com.marijamihajlovska.gymtraininggenerator.data

import com.marijamihajlovska.gymtraininggenerator.model.Difficulty
import com.marijamihajlovska.gymtraininggenerator.model.ExerciseCategory
import com.marijamihajlovska.gymtraininggenerator.model.ExerciseItem

object ExerciseRepository {

    private fun ytEmbed(id: String) = "https://www.youtube.com/embed/$id"
    private fun ytThumb(id: String) = "https://img.youtube.com/vi/$id/mqdefault.jpg"

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
            imageUrl = ytThumb("SCVCLChPQMs"),
            videoUrl = ytEmbed("SCVCLChPQMs")
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
            imageUrl = ytThumb("DbFgADa26kU"),
            videoUrl = ytEmbed("DbFgADa26kU")
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
            imageUrl = ytThumb("IODxDxX7oi4"),
            videoUrl = ytEmbed("IODxDxX7oi4")
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
            imageUrl = ytThumb("Iwe6AmxVf7o"),
            videoUrl = ytEmbed("Iwe6AmxVf7o")
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
            imageUrl = ytThumb("yN6Q1UI_xr0"),
            videoUrl = ytEmbed("yN6Q1UI_xr0")
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
            imageUrl = ytThumb("op9kVnSso6Q"),
            videoUrl = ytEmbed("op9kVnSso6Q")
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
            imageUrl = ytThumb("eGo4IYlbE5g"),
            videoUrl = ytEmbed("eGo4IYlbE5g")
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
            imageUrl = ytThumb("CAwf7n6Luuc"),
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
            imageUrl = ytThumb("G8l_8chR5BE"),
            videoUrl = ytEmbed("G8l_8chR5BE")
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
            imageUrl = ytThumb("GZbfZ033f74"),
            videoUrl = ytEmbed("GZbfZ033f74")
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
            imageUrl = ytThumb("HSoHeSMaFnA"),
            videoUrl = ytEmbed("HSoHeSMaFnA")
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
            imageUrl = ytThumb("2yjwXTZbDnM"),
            videoUrl = ytEmbed("2yjwXTZbDnM")
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
            imageUrl = ytThumb("3VcKaXpzqRo"),
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
            imageUrl = ytThumb("gkIaUVWNQss"),
            videoUrl = ytEmbed("gkIaUVWNQss")
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
            imageUrl = ytThumb("HSoHeSMaFnA"),
            videoUrl = ytEmbed("HSoHeSMaFnA")
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
            imageUrl = ytThumb("6Z15_WdXmVw"),
            videoUrl = ytEmbed("6Z15_WdXmVw")
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
            imageUrl = ytThumb("ykJmrZ5v0Oo"),
            videoUrl = ytEmbed("ykJmrZ5v0Oo")
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
            imageUrl = ytThumb("zC3nLlEvin4"),
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
            imageUrl = ytThumb("Jvj2wV0vOYU"),
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
            imageUrl = ytThumb("6kALZikXxLc"),
            videoUrl = ytEmbed("6kALZikXxLc")
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
            imageUrl = ytThumb("d_KZxkY_Zmk"),
            videoUrl = ytEmbed("d_KZxkY_Zmk")
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
            imageUrl = ytThumb("2-LAMcpzODU"),
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
            imageUrl = ytThumb("Ayn13TC0MEc"),
            videoUrl = ytEmbed("Ayn13TC0MEc")
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
            imageUrl = ytThumb("JCXZell6reo"),
            videoUrl = ytEmbed("JCXZell6reo")
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
            imageUrl = ytThumb("IZxyjW7RZSA"),
            videoUrl = ytEmbed("IZxyjW7RZSA")
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
            imageUrl = ytThumb("QOVaHwm-Q6U"),
            videoUrl = ytEmbed("QOVaHwm-Q6U")
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
            imageUrl = ytThumb("-M4-G8p6rgg"),
            videoUrl = ytEmbed("-M4-G8p6rgg")
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
            imageUrl = ytThumb("8bbE64NuDdI"),
            videoUrl = ytEmbed("8bbE64NuDdI")
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
            imageUrl = ytThumb("1Tq3QdYUuHs"),
            videoUrl = ytEmbed("1Tq3QdYUuHs")
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
            imageUrl = ytThumb("ASdvN_XEl_c"),
            videoUrl = ytEmbed("ASdvN_XEl_c")
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
            imageUrl = ytThumb("Xyd_fa5zoEU"),
            videoUrl = ytEmbed("Xyd_fa5zoEU")
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
            imageUrl = ytThumb("wkD8rjkodUI"),
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
            imageUrl = ytThumb("JB2oyawG9KI"),
            videoUrl = ytEmbed("JB2oyawG9KI")
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
            imageUrl = ytThumb("nmwgirgXLYM"),
            videoUrl = ytEmbed("nmwgirgXLYM")
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
            imageUrl = ytThumb("pMoL3VXzSME"),
            videoUrl = ytEmbed("pMoL3VXzSME")
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
            imageUrl = ytThumb("iSSAk4XNsZ0"),
            videoUrl = ytEmbed("iSSAk4XNsZ0")
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
            imageUrl = ytThumb("dZgVxmf6jeQ"),
            videoUrl = ytEmbed("dZgVxmf6jeQ")
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
            imageUrl = ytThumb("ZZZoCNMU48I"),
            videoUrl = ytEmbed("ZZZoCNMU48I")
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
            imageUrl = ytThumb("52r-czib42M"),
            videoUrl = ytEmbed("52r-czib42M")
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
            imageUrl = ytThumb("u3zgHI8QnqE"),
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
            imageUrl = ytThumb("YSxHGsZbHdQ"),
            videoUrl = ytEmbed("YSxHGsZbHdQ")
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
            imageUrl = ytThumb("C_Y4T-QHkMQ"),
            videoUrl = ytEmbed("C_Y4T-QHkMQ")
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
            imageUrl = ytThumb("L219gSPEp_o"),
            videoUrl = ytEmbed("L219gSPEp_o")
        )
    )

    fun getByCategory(category: ExerciseCategory) = all.filter { it.category == category }

    fun search(query: String) = all.filter {
        it.name.contains(query, ignoreCase = true) ||
        it.muscles.contains(query, ignoreCase = true) ||
        it.equipment.contains(query, ignoreCase = true)
    }
}
