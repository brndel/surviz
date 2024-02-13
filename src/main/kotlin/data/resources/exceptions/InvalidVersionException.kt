package data.resources.exceptions

class InvalidVersionException(
    val foundVersion: String,
    val expectedVersion: String,
): Exception() {
}