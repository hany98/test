CREATE TABLE IF NOT EXISTS HanZimbo.GENERATION_SEED (
    ID                          INT             PRIMARY KEY AUTO_INCREMENT,
    CLIENT_SEED                 VARCHAR(10)     NOT NULL,
    SERVER_SEED                 VARCHAR(64)     NOT NULL
);

CREATE TABLE IF NOT EXISTS HanZimbo.LIMBO_RESULT (
    GENERATION_SEED_ID          INT             NOT NULL,
    NONCE                       INT             NOT NULL,
    RESULT                      DECIMAL         NOT NULL,
    ADD CONSTRAINT PK_LIMBO_RESULT PRIMARY KEY (GENERATION_SEED_ID, NONCE)
);

CREATE TABLE IF NOT EXISTS HanZimbo.ALGO_RESULT (
    GENERATION_SEED_ID          INT             NOT NULL,
    ALGO_ID                     INT             NOT NULL,
    PARAM_ID                    INT             NOT NULL,
    START_NONCE                 INT             NOT NULL,
    END_NONCE                   INT             NOT NULL,
    AMOUNT_RESULT               DECIMAL         NOT NULL,
    AMOUNT_RESULT_PERCENTAGE    DECIMAL         NOT NULL,
    MAX_AMOUNT_LOST             DECIMAL         NOT NULL,
    MAX_AMOUNT_WON              DECIMAL         NOT NULL,
    MAX_CONCURRENT_AMOUNT_LOST  DECIMAL         NOT NULL
    ADD CONSTRAINT PK_ALGO_RESULT PRIMARY KEY (GENERATION_SEED_ID, ALGO_ID, PARAM_ID, START_NONCE, END_NONCE)
);

CREATE TABLE IF NOT EXISTS HanZimbo.THRESHOLD_ALGO_PARAM (
    ID                          INT             PRIMARY KEY AUTO_INCREMENT,
    MULTIPLIER                  DECIMAL         NOT NULL,
    LOSS_STREAK_THRESHOLD       INT             NOT NULL,
    BETS_AFTER_LOSS             INT             NOT NULL
);