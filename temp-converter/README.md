# Temperature Converter

## Run directly from IntelliJ IDEA

1. Open the repository root in IntelliJ IDEA and reload the Maven project from
   `temp-converter/pom.xml`.
2. Select JDK 17 (JavaFX 17.0.2 is used by the project).
3. Create **Run | Edit Configurations | Application**:
   - **Name:** `Temperature Converter`
   - **Module:** `temp-converter`
   - **Main class:** `com.example.MainApp`
   - **Before launch:** keep the Maven build/delegated build enabled.
4. Run that configuration. No `run.bat`, Maven command, or manually assembled
   classpath is required. IntelliJ gets the JavaFX and MariaDB modules from
   Maven.

The project is modular (`com.example.tempconverter`). `module-info.java`
opens the controller package to FXML and declares the JavaFX, SQL, and MariaDB
modules, so the native IntelliJ run configuration uses the same module path as
the Maven project.

## MariaDB setup

Start MariaDB, then run `init.sql` once in a MariaDB client. It creates the
`converterdb` database and the `conversion_history` table used by the
application. The default local connection is:

```text
jdbc:mariadb://localhost:3306/converterdb
user: root
password: root
```

For another account, set `DB_URL`, `DB_USER`, and `DB_PASS` as environment
variables in the IntelliJ run configuration. Java system properties with the
same names are also accepted. A failed save is logged clearly, while the
conversion result remains visible.

## Five conversions and screenshots

Enter a Celsius value and click **Convert & Save** five times, for example
`0`, `10`, `20`, `37`, and `100`. The result is shown in Fahrenheit and each
successful save is inserted into `conversion_history`.

For the submission screenshots, run the same configuration and select
**English**, **Japanese**, and **Arabic** from the language selector. Capture
the window after the labels update in each language; Arabic is displayed
right-to-left. The selected language is also stored with each conversion.
