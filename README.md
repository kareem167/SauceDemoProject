# SauceDemo UI Test Automation Framework

An end-to-end test automation framework for [SauceDemo](https://www.saucedemo.com/). It uses Java, Selenium WebDriver, TestNG, and the Page Object Model (POM) to test the main customer journey: login, product browsing, cart management, checkout, and order-document generation.

## Highlights

- Page Object Model for clear, maintainable UI interactions
- Cross-browser execution in Chrome, Firefox, and Microsoft Edge
- Parallel TestNG method execution (4 threads)
- Configurable SauceDemo test user and checkout data
- Checkout PDF download, file, size, and content validation
- Allure reporting with environment details
- Automatic screenshot capture and Allure attachment when a test fails
- Headless browser configuration for GitHub Actions

## Test coverage

| Area | Scenarios covered |
| --- | --- |
| Authentication | Successful login using SauceDemo credentials |
| Inventory | Product count, names, prices, cart actions, sorting, cart navigation, logout |
| Product details | Name, description, price, image, add/remove cart actions, back navigation |
| Cart | Cart title, item count, price, remove item, continue shopping, checkout navigation |
| Checkout | Customer details, overview, totals, payment information, completion, return to products |
| Order PDF | Download confirmation, PDF file type, non-empty content, first name, last name, and postal-code validation |

The checked-in Allure report shows a successful run of **40 tests**.

## Screenshots and test evidence

The framework captures a browser screenshot **only when a test fails**. This keeps successful runs clean while ensuring that failures have useful visual evidence.

When a failure happens, the screenshot is:

1. Saved as `target/screenshots/<test-method-name>.png`.
2. Attached to that test’s result in the Allure report as `screenshot`.

After generating the report, open the failed test in Allure and view the attachment in its details. You can also open the PNG directly from `target/screenshots/`.

> `target/` is intentionally ignored by Git, so failure screenshots are local build evidence. To display screenshots on GitHub, copy selected images into a committed directory such as `docs/screenshots/` and add them below using `![description](docs/screenshots/file-name.png)`.

## Tech stack

- Java 23
- Maven
- Selenium WebDriver 4.45.0
- TestNG 7.12.0
- Allure TestNG 2.35.3
- Apache PDFBox 3.0.5
- Apache Commons IO 2.20.0

## Project structure

```text
src/
├── main/java/
└── test/java/com/SauceDemo/
    ├── base/        # Test lifecycle, failure screenshots, Allure environment
    ├── config/      # SauceDemo users and checkout data properties
    ├── factory/     # Chrome, Firefox, and Edge driver setup
    ├── pages/       # Page Object Model classes
    ├── testcases/   # TestNG test suites
    └── utils/       # Property loading and PDF validation utilities
allure-results/      # Raw results produced by Allure
allure-report/       # Generated HTML Allure report
downloads/           # PDFs created during checkout tests
```

## Prerequisites

- JDK 23
- Maven 3.9+
- Google Chrome, Mozilla Firefox, or Microsoft Edge

Selenium Manager normally downloads and manages the matching browser driver automatically.

## Run the test suite

From the project root:

```bash
mvn clean test
```

Chrome is the default browser. To choose another supported browser:

```bash
mvn clean test -Dbrowser=firefox
mvn clean test -Dbrowser=edge
```

## Test users and test data

User credentials and checkout inputs are held in simple `.properties` files under `src/test/java/com/SauceDemo/config/`.

### SauceDemo users

| Configuration file | User | Purpose |
| --- | --- | --- |
| `standard_user.properties` | `standard_user` | Default user for the main positive test flow |
| `problem_user.properties` | `problem_user` | SauceDemo user for problem-behaviour testing |
| `locked_out_user.properties` | `locked_out_user` | SauceDemo user for login restriction testing |
| `error_user.properties` | `error_user` | Available test credential file |
| `performance_glitch_user.properties` | `performance_glitch_user` | Available test credential file |
| `visual_user.properties` | `visual_user` | Available test credential file |

The current user-selection utility supports these command-line values:

```bash
mvn test -Denvuser=standard_user
mvn test -Denvuser=problem_user
mvn test -Denvuser=locked_out_user
```

### Checkout data

The project includes two checkout data files:

| File | First name | Last name | Postal code |
| --- | --- | --- | --- |
| `testdata1.properties` | Kareem | Gomaa | 12345 |
| `testdata2.properties` | mostafa | nour | 43218 |

The default active data set is `testdata1`:

```bash
mvn test -DtestData=testdata1
```

## PDF validation

Checkout tests generate an order confirmation PDF in `downloads/`. The suite verifies that the file:

- was downloaded within the expected time;
- is a real PDF;
- is not empty; and
- contains the submitted first name, last name, and postal code.

## Allure reporting

Run the suite first, then generate and open the interactive report:

```bash
mvn allure:serve
```

To create a static report without starting a local server:

```bash
mvn allure:report
```

The generated report is available at `allure-report/index.html`. It includes test status, duration, feature/story grouping, environment details, and screenshot attachments for failed tests.

## GitHub Actions readiness

When `GITHUB_ACTIONS` is set, the driver factory runs supported browsers headlessly and uses a 1920×1080 viewport. This allows the same tests to run locally and in a GitHub Actions workflow.

## Important notes

- This project is for the SauceDemo demo application and uses its public demo credentials.
- Downloaded PDFs are generated artifacts stored in `downloads/`.
- `target/` is excluded through `.gitignore`; it contains Maven output, test reports, and failure screenshots.
- The repository currently has no Git metadata initialized. Before uploading it, run `git init`, commit the files, and push to your GitHub repository.
