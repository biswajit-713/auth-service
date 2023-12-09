CREATE TABLE IF NOT EXISTS oauth2_registered_client(
    id varchar(100) NOT NULL,
    client_id varchar(100) NOT NULL,
    client_id_issued_at timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL,
    client_secret varchar(100) DEFAULT NULL,
    client_secret_expires_at timestamp  NULL DEFAULT NULL,
    client_name varchar(200) NOT NULL,
    client_authentication_methods varchar(1000) NOT NULL,
    authorization_grant_types varchar(100) NOT NULL,
    redirect_uris varchar(1000) DEFAULT NULL,
    post_logout_redirect_uris varchar(1000) DEFAULT NULL,
    scopes varchar(1000) NOT NULL,
    client_settings varchar(2000) NOT NULL,
    token_settings varchar(2000) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS users (
    username varchar(200) NOT NULL PRIMARY KEY,
    password varchar(500) NOT NULL,
    enabled boolean NOT NULL
);

CREATE TABLE IF NOT EXISTS authorities (
    username varchar(200) NOT NULL PRIMARY KEY,
    authority varchar(50) NOT NULL,
    constraint fk_authorities_user FOREIGN KEY (username) REFERENCES users(username),
    constraint username_authority UNIQUE(username, authority)
);