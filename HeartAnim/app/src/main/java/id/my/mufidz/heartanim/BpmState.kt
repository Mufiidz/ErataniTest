package id.my.mufidz.heartanim

data class BpmState(val bpm: Int) {
    val durationMs: Int
        get() = if (bpm <= 0)
            0
        else
            (60000f / bpm.toFloat()).toInt()

    val halfDurationMs: Int
        get() = durationMs / 2
}
