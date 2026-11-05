
## Database Localization Approach

### Chosen Approach: Translation Table

For localizing unit names and descriptions, I selected the **Translation Table** approach. This database design separates core unit entities from their localized text attributes.

#### Database Schema
The design consists of two main tables:
1. `unit(id INT PRIMARY KEY, unit_key VARCHAR(50))` – Stores language-neutral base data and identifier keys.
2. `unit_translation(unit_id INT, lang VARCHAR(5), name VARCHAR(100), description VARCHAR(255))` – Stores text translations linked to the base unit via a foreign key (`unit_id`), using `(unit_id, lang)` as a composite primary key.

#### Justification for Selection
- **Clean Normalization:** Non-text properties belong strictly to the `unit` table, preventing data duplication across languages.
- **Scalability & Schema Stability:** Adding a new language only requires inserting new rows into `unit_translation` without modifying the database schema (`ALTER TABLE`) or creating new tables.
- **Robust Fallback Querying:** Combining `LEFT JOIN` and `COALESCE` allows effortless fallback to English whenever a translation for a target language is missing.
- **Industry Standard:** This structure represents standard production database architecture for multi-language systems.
