-- -----------------------------------------------------
-- Table `user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `user` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(10) NULL,
    `email` VARCHAR(20) NULL,
    `creation_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `modification_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `password` VARCHAR(20) NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `schedule`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `schedule` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `todo` VARCHAR(20) NOT NULL,
    `creation_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `modification_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `user_id` INT NOT NULL,
    PRIMARY KEY (`id`, `user_id`),
    INDEX `fk_schedule_user_idx` (`user_id` ASC) VISIBLE,
    CONSTRAINT `fk_schedule_user`
    FOREIGN KEY (`user_id`)
    REFERENCES `schedule_management`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
) ENGINE = InnoDB;


-- -----------------------------------------------------
-- 일정 생성을 하는 query를 작성
-- -----------------------------------------------------
INSERT INTO `schedule` (user_id, todo) VALUES ();

-- -----------------------------------------------------
-- 전체 일정을 조회하는 query를 작성
-- -----------------------------------------------------
SELECT todo, creation_date, modification_data from `schedule`
WHERE user_id = (SELECT id FROM `user` WHERE `name` = '작성자') ORDER BY modification_data DESC;

-- -----------------------------------------------------
-- 선택 일정을 조회하는 query를 작성
-- -----------------------------------------------------
SELECT todo, creation_date, modification_data from `schedule`
WHERE id = '스케줄 아이디';

-- -----------------------------------------------------
-- 선택한 일정을 수정하는 query를 작성
-- -----------------------------------------------------
UPDATE `schedule` SET todo='값', author_name='값' WHERE id = '스케줄 아이디';

-- -----------------------------------------------------
-- 선택한 일정을 삭제하는 query를 작성
-- -----------------------------------------------------
DELETE FROM `schedule` WHERE id = '스케줄 아이디'