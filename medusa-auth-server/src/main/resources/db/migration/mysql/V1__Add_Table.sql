/*==============================================================*/
/* Table: oauth_client_details                                  */
/*==============================================================*/
/*resource_id通过erureka/nacos接口获取*/
/*scope用来表示client_id的用途：web/api */
DROP TABLE IF EXISTS oauth_client_details;
CREATE TABLE oauth_client_details (
  id                      INT NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT '主键',
  client_id               NVARCHAR(255) NOT NULL COMMENT '用于唯一标识每一个客户端(client)，在实际应用中的另一个名称叫appKey',
  resource_ids            NVARCHAR(256) NULL COMMENT '客户端所能访问的资源id集合,多个资源时用逗号(,)分隔',
  client_secret           NVARCHAR(256) NULL COMMENT '用于指定客户端(client)的访问密匙',
  scope                   NVARCHAR(256) NULL COMMENT '指定客户端申请的权限范围',
  authorized_grant_types  NVARCHAR(256) NULL COMMENT '指定客户端申请的权限范围，指定客户端支持的grant_type,可选值包括authorization_code,password,refresh_token,implicit,client_credentials, 若支持多个grant_type用逗号(,)分隔',
  web_server_redirect_uri NVARCHAR(256) NULL COMMENT '客户端的重定向URI',
  authorities             NVARCHAR(256) NULL COMMENT '指定客户端所拥有的Spring Security的权限值,可选, 若有多个权限值,用逗号(,)分隔, 如: "ROLE_UNITY,ROLE_USER',
  access_token_validity   INT           NULL COMMENT '设定客户端的access_token的有效时间值(单位:秒)',
  refresh_token_validity  INT           NULL COMMENT '设定客户端的refresh_token的有效时间值(单位:秒)',
  additional_information  NVARCHAR(256) NULL COMMENT '这是一个预留的字段,在Oauth的流程中没有实际的使用,可选,但若设置值,必须是JSON格式的数据',
  auto_approve             NVARCHAR(256) NULL COMMENT '设置用户是否自动Approval操作, 默认值为 false, 可选值包括 true,false, read,write',
  bind                    SMALLINT      DEFAULT '1'
) COMMENT '客户端信息表' ;

/*==============================================================*/
/* Table: oauth_client_token-该表用于在客户端系统中存储从服务端获取的token数据*/
/*==============================================================*/
DROP TABLE IF EXISTS oauth_client_token;
CREATE TABLE oauth_client_token (
  token_id          NVARCHAR(256)   NULL COMMENT '从服务器端获取到的access_token的值.',
  token             VARBINARY(8000) NULL COMMENT '这是一个二进制的字段, 存储的数据是OAuth2AccessToken.java对象序列化后的二进制数据.',
  authentication_id NVARCHAR(255)   NOT NULL PRIMARY KEY COMMENT '该字段具有唯一性, 是根据当前的username(如果有),client_id与scope通过MD5加密生成的.',
  user_name         NVARCHAR(256)   NULL COMMENT '登录时的用户名',
  client_id         NVARCHAR(256)   NULL
)  COMMENT '客户端授权令牌表';

/*==============================================================*/
/* Table: oauth_access_token                                    */
/*==============================================================*/
DROP TABLE IF EXISTS oauth_access_token;
CREATE TABLE oauth_access_token (
  token_id          NVARCHAR(256)   NOT NULL PRIMARY KEY COMMENT '该字段的值是将access_token的值通过MD5加密后存储的',
  token             VARBINARY(8000) NULL COMMENT '存储将OAuth2AccessToken.java对象序列化后的二进制数据, 是真实的AccessToken的数据值',
  authentication_id NVARCHAR(255)   NOT NULL COMMENT '该字段具有唯一性, 其值是根据当前的username(如果有),client_id与scope通过MD5加密生成的',
  user_name         NVARCHAR(256)   NULL COMMENT '登录时的用户名, 若客户端没有用户名(如grant_type="client_credentials"),则该值等于client_id',
  client_id         NVARCHAR(256)   NULL,
  authentication    VARBINARY(8000) NULL COMMENT '存储将OAuth2Authentication对象序列化后的二进制数据.',
  refresh_token     NVARCHAR(256)   NULL COMMENT '该字段的值是将refresh_token的值通过MD5加密后存储的'
) COMMENT '访问令牌表';

/*==============================================================*/
/* Table: oauth_refresh_token                                   */
/*==============================================================*/
DROP TABLE IF EXISTS oauth_refresh_token;
CREATE TABLE oauth_refresh_token (
  token_id       NVARCHAR(256)   NOT NULL PRIMARY KEY COMMENT '该字段的值是将refresh_token的值通过MD5加密后存储的',
  token          VARBINARY(8000) NULL COMMENT '存储将OAuth2RefreshToken对象序列化后的二进制数据',
  authentication VARBINARY(8000) NULL COMMENT '存储将OAuth2Authentication对象序列化后的二进制数据'
) COMMENT '更新令牌表';

/*==============================================================*/
/* Table: oauth_code                                            */
/*==============================================================*/
DROP TABLE IF EXISTS oauth_code;
CREATE TABLE oauth_code (
  code           NVARCHAR(256)   NOT NULL PRIMARY KEY COMMENT '存储服务端系统生成的code的值(未加密)',
  authentication VARBINARY(8000) NULL COMMENT '存储将AuthorizationRequestHolder对象序列化后的二进制数据'
) COMMENT '授权码表';


/*==============================================================*/
/* Table: oauth_approvals                                       */
/*==============================================================*/
DROP TABLE IF EXISTS oauth_approvals;
CREATE TABLE oauth_approvals (
  userId         NVARCHAR(256) NOT NULL PRIMARY KEY COMMENT '登录的用户名',
  clientId       NVARCHAR(256) NULL COMMENT '客户端ID',
  scope          NVARCHAR(256) NULL COMMENT '申请的权限',
  status         NVARCHAR(10)  NULL COMMENT '状态（Approve或Deny）',
  expiresAt      DATE          NULL COMMENT '过期时间',
  lastModifiedAt DATE          NULL COMMENT '最终修改时间'
) COMMENT '授权记录表';

-- ----------------------------
-- Auth自己的用户信息表
-- ----------------------------
drop table if exists oauth_sys_user;
create table oauth_sys_user (
  user_id           bigint(20)      not null auto_increment    comment '用户ID',
  user_name         varchar(30)     not null                   comment '用户名称',
  login_name        VARCHAR(100) NOT NULL                      COMMENT '登录名称',
  login_password    VARCHAR(200) NOT NULL                      COMMENT '登录密码',
  user_type         varchar(2)      default '00'               comment '用户类型（1:系统用户）',
  phone             varchar(11)     default ''                 comment '手机号码',
  email             varchar(50)     default null               comment '电子邮箱',
  sex               char(1)         default '0'                comment '用户性别（1男 2女 0未知）',
  status            char(1)         default '0'                comment '帐号状态（1正常 0停用）',
  create_time       datetime        default NOW()              comment '创建时间',
  update_time       datetime        default NOW()              comment '更新时间',
  description       varchar(2000)    default null               comment '描述',
  logic_delete      char(1)         default '0'                comment '逻辑删除标志（0代表存在 1代表删除）',
  primary key (user_id)
) comment = 'Auth用户信息表';
