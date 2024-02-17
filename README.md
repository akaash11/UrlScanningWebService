
# URL Scanner Web Service

This project consists a web service API to scan a given URL and return various artifacts from it using Playwright.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites

What things you need to install the software and how to install them:

- Java JDK 17
- Maven
- MongoDB

### Installing

Steps to get a development environment running:

1. **Clone the repository**

```bash
git clone https://github.com/akaash11/UrlScanningWebService.git
```

2. **Navigate to the project directory**

```bash
cd UrlScanningWebService
```

3. **Use Maven to install dependencies**

```bash
mvn clean install
```

## Dependency Management

This project uses Maven for managing its dependencies. Check the `pom.xml` file for the list of dependencies.

## Database Configuration

### MongoDB Setup

This project uses MongoDB as its database. Below are the details for the database configuration:

- **DB Name**: `UrlScanDb`
- **Collection Name**: `webCollection`

#### Fields in `webCollection`:

- `ipAddress`: Stores the IP address related to the web scans.
- `sslDetails`: Contains details about the SSL certificate of the scanned website.
- `pageSource`: The HTML source code of the webpage.
- `locale`: The locale setting of the webpage (natural language content)
- `language`: The primary language of the webpage content (natural language content)

Ensure MongoDB is running and accessible at `mongodb://localhost:27017` (default URI) or adjust your application configuration accordingly to match your MongoDB setup.

## Output

The program saves screenshots when it accesses any page or is redirected to a new page. The following files will be created at project level:

- `playwright.png`: source URL screenshot
- `playwright_before_redirect.png`: source URL screenshot
- `playwright_after_redirect.png`: destination URL screenshot (redirection)

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details.
