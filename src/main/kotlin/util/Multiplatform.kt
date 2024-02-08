package util

fun platformPath(windows: (String) -> String, linux: (String) -> String, mac: (String) -> String): String {
    val os = System.getProperty("os.name").lowercase()
    val name = System.getProperty("user.name")
    return if (os.contains("win")) {
        windows(name)
    } else if (os.contains("mac")) {
        mac(name)
    } else {
        linux(name)
    }
}