object Git {
    fun getRevisionId(): String {
        return Runtime.getRuntime().exec("git rev-parse --short HEAD")
                .inputStream.bufferedReader().readText().trim()
    }

    fun getRevisionNumber(): Int {
        val output = Runtime.getRuntime().exec("git rev-list --count HEAD")
                .inputStream.bufferedReader().readText().trim()
        return if (output.isEmpty()) {
            1 // Return 1 as default value because this value is used as versionCode and have to be positive
        } else {
            output.replace("+", "").toInt()
        }
    }
}
