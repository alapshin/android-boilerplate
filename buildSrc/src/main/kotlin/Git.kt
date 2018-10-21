object Git {
    fun getRevisionId(): String {
        return Runtime.getRuntime().exec("git rev-parse --short HEAD")
                .inputStream.bufferedReader().readText().trim()
    }

    fun getRevisionNumber(): Int {
        val output = Runtime.getRuntime().exec("git rev-list --count HEAD")
                .inputStream.bufferedReader().readText().trim()
        return if (output.isEmpty()) {
            0
        } else {
            output.replace("+", "").toInt()
        }
    }
}
