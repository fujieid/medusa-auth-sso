INSERT INTO oauth_client_details (client_id,resource_ids,client_secret,scope,authorized_grant_types,web_server_redirect_uri,authorities,access_token_validity,refresh_token_validity,additional_information,auto_approve,bind)
VALUES ('auth', 'haoyu-auth-server,haoyu-middle-server', '$2a$10$7PtegA6OsLb402nVZ1C4oOTFvgE42kfnBuDuSGY7JV5plnSH7BMRm', 'read,write', 'authorization_code,client_credentials,password,refresh_token', NULL, NULL, 100000, NULL, NULL, NULL,0);

INSERT INTO oauth_client_details (client_id,resource_ids,client_secret,scope,authorized_grant_types,web_server_redirect_uri,authorities,access_token_validity,refresh_token_validity,additional_information,auto_approve,bind)
VALUES ('middle', 'haoyu-auth-server,haoyu-middle-server,haoyu-open-api', '$2a$10$7PtegA6OsLb402nVZ1C4oOTFvgE42kfnBuDuSGY7JV5plnSH7BMRm', 'read,write', 'authorization_code,client_credentials,password,refresh_token', NULL, 'ADMIN', 100000, NULL, NULL, NULL,0);

INSERT INTO oauth_client_details (client_id,resource_ids,client_secret,scope,authorized_grant_types,web_server_redirect_uri,authorities,access_token_validity,refresh_token_validity,additional_information,auto_approve,bind)
VALUES ('openapi', 'haoyu-auth-server,haoyu-middle-server,haoyu-open-api', '$2a$10$7PtegA6OsLb402nVZ1C4oOTFvgE42kfnBuDuSGY7JV5plnSH7BMRm', 'read,write', 'client_credentials,password,refresh_token', NULL, 'ADMIN', 100000, NULL, NULL, NULL,0);

COMMIT;

/** 插入初始化用户-插入用户表记录 */
INSERT INTO `oauth_sys_user`(`user_id`, `user_name`, `login_name`, `login_password`, `user_type`, `phone`, `status`,  `description`, `create_time`, `update_time`,`logic_delete`)
 VALUES (-1, '超级管理员', 'superadmin', '$2a$10$y5P0.BI3zsB5H5b4hGrgaOEROcDtUwey93g7FxVmhAOQiPPz2uey6', 1, '13696560594', 1, NULL, now(), now(), 0);

INSERT INTO `oauth_sys_user`(`user_id`, `user_name`, `login_name`, `login_password`, `user_type`,`phone`, `status`, `description`, `create_time`, `update_time`, `logic_delete`)
 VALUES (1, '平台管理员', 'admin', '$2a$10$y5P0.BI3zsB5H5b4hGrgaOEROcDtUwey93g7FxVmhAOQiPPz2uey6', 1, '13515532332', 1, NULL, now(), now(), 0);

COMMIT ;