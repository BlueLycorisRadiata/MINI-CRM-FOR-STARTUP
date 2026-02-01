# Error Pages Documentation

## Overview

Custom error pages have been created for centralized error handling in the CRM application. These pages provide a user-friendly interface when errors occur.

## Error Pages

### 404 - Page Not Found
**File**: `error/404.html`

Displayed when a user tries to access a URL that doesn't exist.

**Features**:
- Clear 404 error message in Vietnamese
- Helpful navigation buttons (Back to Dashboard, Go Back)
- Link to useful pages (Dashboard, Login)
- Uses the standard navbar fragment for consistency

### 500 - Internal Server Error
**File**: `error/500.html`

Displayed when an internal server error occurs.

**Features**:
- Clear 500 error message
- Reload button to retry the request
- Error details shown in development mode (when `error` attribute is available)
- Navigation back to Dashboard

### Generic Error Page
**File**: `error.html`

Fallback page for other HTTP errors (403, 400, etc.).

**Features**:
- Dynamic error status and message display
- Stack trace in development mode (when `?trace=true` parameter is present)
- Timestamp of when the error occurred
- Navigation options

## Directory Structure

```
templates/
├── error.html              # Generic error page (fallback)
└── error/
    ├── 404.html           # Page Not Found
    └── 500.html           # Internal Server Error
```

## How It Works

Spring Boot automatically detects custom error pages based on:
1. **HTTP Status Code**: Place files like `404.html`, `500.html` in `templates/error/` directory
2. **Generic Fallback**: Place `error.html` in `templates/` root directory

When an error occurs:
1. Spring Boot checks for specific error page (e.g., `error/404.html`)
2. If not found, falls back to generic `error.html`
3. If no custom pages exist, shows default whitelabel error page (now disabled)

## Configuration

In `application.properties`:

```properties
# Error Handling
server.error.include-message=always
server.error.include-binding-errors=always
server.error.whitelabel.enabled=false
server.error.include-stacktrace=on_param
```

- `whitelabel.enabled=false` - Disables Spring's default error page
- `include-stacktrace=on_param` - Shows stack trace only when `?trace=true` is in URL

## Available Error Attributes

Spring Boot provides these attributes in error pages:

| Attribute | Description | Example |
|-----------|-------------|---------|
| `timestamp` | When error occurred | `2026-01-29T00:40:00` |
| `status` | HTTP status code | `404`, `500` |
| `error` | Short error description | `Not Found`, `Internal Server Error` |
| `message` | Detailed error message | `No message available` |
| `path` | URL that caused the error | `/customers/999` |
| `trace` | Stack trace (if enabled) | Full exception trace |

## Examples

### 404 Error Display
```
404
Oops! Không tìm thấy trang
Trang bạn đang tìm kiếm không tồn tại hoặc đã bị di chuyển.

[Về Dashboard] [Quay lại]
```

### 500 Error Display
```
500
Oops! Lỗi máy chủ
Đã xảy ra lỗi trong quá trình xử lý yêu cầu của bạn.

[Về Dashboard] [Thử lại]
```

## Testing Error Pages

To test the error pages:

1. **404 Error**: Navigate to any non-existent URL
   - Example: `http://localhost:8080/nonexistent-page`

2. **500 Error**: Trigger a server exception
   - Example: Cause a database error or null pointer exception

3. **Generic Error**: Will be shown for other HTTP errors

## Customization

To add more specific error pages:
1. Create a new file in `templates/error/` directory
2. Name it with the HTTP status code (e.g., `403.html` for Forbidden)
3. Use the same structure as existing error pages with fragments

Example for 403 Forbidden:
```html
<!doctype html>
<html lang="vi" xmlns:th="http://www.thymeleaf.org">
<head th:replace="~{fragments :: head('403 - Truy cập bị từ chối')}"></head>
<body>
  <div th:replace="~{fragments :: navbar}"></div>
  <!-- Your custom 403 content here -->
</body>
</html>
```

## Benefits

✅ **Consistent UI** - All error pages use the same navbar and styling  
✅ **User-Friendly** - Clear messages in Vietnamese with helpful actions  
✅ **Developer-Friendly** - Stack traces available in development mode  
✅ **Maintainable** - Uses fragment structure for easy updates  
✅ **Professional** - No more default Spring whitelabel error pages
