MERGE INTO ROLES(id, name) VALUES(1, 'ROLE_ADMIN');
MERGE INTO USERS(id, name, email, password, role_id) VALUES(1, 'Yurii Kotsiuba', 'jurok3x@gmail.com', 'metropoliten', 1);
MERGE INTO PERMISSIONS(id, name) VALUES(1, 'user:read');
MERGE INTO PERMISSIONS(id, name) VALUES(2, 'user:write');
MERGE INTO ROLE_PERMISSIONS(role_id, permission_id) VALUES(1, 1);
MERGE INTO ROLE_PERMISSIONS(role_id, permission_id) VALUES(1, 2);