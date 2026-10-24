# Student Management App - Student Registration

Suhani Bighane's contribution for the Practical DevOps collaboration activity.
This Maven module provides a small desktop registration screen backed by an
in-memory service.

## Features

- Registers students with an ID, full name, email address, and course.
- Validates required fields and email format.
- Rejects duplicate student IDs and email addresses (case-insensitive).
- Lists registered students during the current application run.

## Run

Requires Java 17 or newer and Maven.

```powershell
mvn test
mvn package
java -cp target/classes com.sms.StudentRegistrationApp
```

If the Maven Exec plugin is not installed, compile and run directly:

```powershell
javac -d target/classes src/main/java/com/sms/*.java
java -cp target/classes com.sms.StudentRegistrationApp
```

Student records are held in memory and are cleared when the application closes.
This contribution is on `feature/student-registration`; pushing the branch
and completing pull-request review and merge require repository/team access.
