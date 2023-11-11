CREATE TABLE IF NOT EXISTS member (
    id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    created_at TIMESTAMP(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
    updated_at TIMESTAMP(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
    deleted_at TIMESTAMP(6),
    team_id bigint UNSIGNED DEFAULT NULL,
    user_id bigint UNSIGNED DEFAULT NULL,
    KEY `FK9mgjw7mdocnytoi2a45jo4vgc` (team_id),
    KEY `FKgox76pyv0kmrobtr95xyngamo` (user_id),
    CONSTRAINT `FK9mgjw7mdocnytoi2a45jo4vgc` FOREIGN KEY (team_id) REFERENCES team (`id`),
    CONSTRAINT `FKgox76pyv0kmrobtr95xyngamo` FOREIGN KEY (user_id) REFERENCES user (`id`),
    UNIQUE KEY `member_uk` (team_id,user_id)
);