
insert into role (id, name, active) values (1, 'Admin', true);
insert into role (id, name, active) values (2, 'User', true );
insert into user (id, name, email, last_name, password, username, role_id) values ( 1, 'Alicia', 'alicruz0703@gmail.com', 'Cruz', '$2a$10$XUZRhDaN7U2oNUMZtN1wNOzPL2nUptG0K29dxXmAFH9eXbGCOrTKS', 'ali00', 1);

insert into http_status_code (id, name, code, type) values ( 1, 'Continue', 100, 'Informational Response');
insert into http_status_code (id, name, code, type) values ( 2, 'Switching Protocols', 101, 'Informational Response');
insert into http_status_code (id, name, code, type) values ( 3, 'Processing', 102, 'Informational Response');

insert into http_status_code (id, name, code, type) values ( 4, 'OK', 200, 'Success');
insert into http_status_code (id, name, code, type) values ( 5, 'Created', 201, 'Success');
insert into http_status_code (id, name, code, type) values ( 6, 'Accepted', 202, 'Success');
insert into http_status_code (id, name, code, type) values ( 7, 'Non-Authoritative Information', 203, 'Success');
insert into http_status_code (id, name, code, type) values ( 8, 'No Content', 204, 'Success');
insert into http_status_code (id, name, code, type) values ( 9, 'Reset Content', 205, 'Success');
insert into http_status_code (id, name, code, type) values ( 10, 'Partial Content', 206, 'Success');
insert into http_status_code (id, name, code, type) values ( 11, 'Multi-Status', 207, 'Success');
insert into http_status_code (id, name, code, type) values ( 12, 'Already Reported', 208, 'Success');
insert into http_status_code (id, name, code, type) values ( 13, 'IM Used', 226, 'Success');

insert into http_status_code (id, name, code, type) values ( 14, 'Multiple Choices', 300, 'Redirection');
insert into http_status_code (id, name, code, type) values ( 15, 'Moved Permanently', 301, 'Redirection');
insert into http_status_code (id, name, code, type) values ( 16, 'Found', 302, 'Redirection');
insert into http_status_code (id, name, code, type) values ( 17, 'See Other', 303, 'Redirection');
insert into http_status_code (id, name, code, type) values ( 18, 'Not Modified', 304, 'Redirection');
insert into http_status_code (id, name, code, type) values ( 19, 'Use Proxy', 305, 'Redirection');
insert into http_status_code (id, name, code, type) values ( 20, 'Switch Proxy', 306, 'Redirection');
insert into http_status_code (id, name, code, type) values ( 21, 'Temporary Redirect', 307, 'Redirection');
insert into http_status_code (id, name, code, type) values ( 22, 'Permanent Redirect', 308, 'Redirection');

insert into http_status_code (id, name, code, type) values ( 23, 'Bad Request', 400, 'Client Errors');
insert into http_status_code (id, name, code, type) values ( 24, 'Unauthorized', 401, 'Client Errors');
insert into http_status_code (id, name, code, type) values ( 25, 'Payment Required', 402, 'Client Errors');
insert into http_status_code (id, name, code, type) values ( 26, 'Forbidden', 403, 'Client Errors');
insert into http_status_code (id, name, code, type) values ( 27, 'Not Found', 404, 'Client Errors');
insert into http_status_code (id, name, code, type) values ( 28, 'Method Not Allowed', 405, 'Client Errors');
insert into http_status_code (id, name, code, type) values ( 29, 'Not Acceptable', 406, 'Client Errors');
insert into http_status_code (id, name, code, type) values ( 30, 'Proxy Authentication Required', 407, 'Client Errors');
insert into http_status_code (id, name, code, type) values ( 31, 'Request Timeout', 408, 'Client Errors');
insert into http_status_code (id, name, code, type) values ( 32, 'Conflict', 409, 'Client Errors');
insert into http_status_code (id, name, code, type) values ( 33, 'Gone', 410, 'Client Errors');
insert into http_status_code (id, name, code, type) values ( 34, 'Length Required', 411, 'Client Errors');
insert into http_status_code (id, name, code, type) values ( 35, 'Precondition Failed', 412, 'Client Errors');
insert into http_status_code (id, name, code, type) values ( 36, 'Request Entity Too Large', 413, 'Client Errors');
insert into http_status_code (id, name, code, type) values ( 37, 'Request-URI Too Long', 414, 'Client Errors');
insert into http_status_code (id, name, code, type) values ( 38, 'Unsupported Media Type', 415, 'Client Errors');
insert into http_status_code (id, name, code, type) values ( 39, 'Requested Range Not Satisfiable ', 416, 'Client Errors');
insert into http_status_code (id, name, code, type) values ( 40, 'Expectation Failed', 417, 'Client Errors');
insert into http_status_code (id, name, code, type) values ( 41, 'Im a teapot', 418, 'Client Errors');
insert into http_status_code (id, name, code, type) values ( 42, 'Enhance Your Calm', 420, 'Client Errors');
insert into http_status_code (id, name, code, type) values ( 43, 'Unprocessable Entity', 422, 'Client Errors');
insert into http_status_code (id, name, code, type) values ( 44, 'Locked', 423, 'Client Errors');
insert into http_status_code (id, name, code, type) values ( 45, 'Failed Dependency', 424, 'Client Errors');
insert into http_status_code (id, name, code, type) values ( 46, 'Unordered Collection', 425, 'Client Errors');
insert into http_status_code (id, name, code, type) values ( 47, 'Upgrade Required', 426, 'Client Errors');
insert into http_status_code (id, name, code, type) values ( 48, 'Precondition Required', 428, 'Client Errors');
insert into http_status_code (id, name, code, type) values ( 49, 'Too Many Requests', 429, 'Client Errors');
insert into http_status_code (id, name, code, type) values ( 50, 'Request Header Fields Too Large', 431, 'Client Errors');
insert into http_status_code (id, name, code, type) values ( 51, 'No Response', 444, 'Client Errors');
insert into http_status_code (id, name, code, type) values ( 52, 'Retry With', 449, 'Client Errors');
insert into http_status_code (id, name, code, type) values ( 53, 'Blocked by Windows Parental Control', 450, 'Client Errors');
insert into http_status_code (id, name, code, type) values ( 54, 'Client Closed Request', 499, 'Client Errors');

insert into http_status_code (id, name, code, type) values ( 55, 'Internal Server Error', 500, 'Server Errors');
insert into http_status_code (id, name, code, type) values ( 56, 'Not Implemented', 501, 'Server Errors');
insert into http_status_code (id, name, code, type) values ( 57, 'Bad Gateway', 502, 'Server Errors');
insert into http_status_code (id, name, code, type) values ( 58, 'Service Unavailable', 503, 'Server Errors');
insert into http_status_code (id, name, code, type) values ( 59, 'Gateway Timeout', 504, 'Server Errors');
insert into http_status_code (id, name, code, type) values ( 60, 'Http Version NOt Supported', 505, 'Server Errors');
insert into http_status_code (id, name, code, type) values ( 61, 'Variant Also Negotiates', 506, 'Server Errors');
insert into http_status_code (id, name, code, type) values ( 62, 'Insufficient Storage', 507, 'Server Errors');
insert into http_status_code (id, name, code, type) values ( 63, 'Bandwidth Limit Exceeded', 509, 'Server Errors');
insert into http_status_code (id, name, code, type) values ( 64, 'Not Extended', 510, 'Server Errors');

insert into content_type(id, name) values ( 1, 'application/json');
insert into content_type(id, name) values ( 2, 'application/x-www-form-urlencoded');
insert into content_type(id, name) values ( 3, 'application/xhtml+xml');
insert into content_type(id, name) values ( 4, 'application/xml');
insert into content_type(id, name) values ( 5, 'multipart/form-data');
insert into content_type(id, name) values ( 6, 'text/css');
insert into content_type(id, name) values ( 7, 'text/csv');
insert into content_type(id, name) values ( 8, 'text/html');
insert into content_type(id, name) values ( 9, 'text/plain');
insert into content_type(id, name) values ( 10, 'text/xml');

