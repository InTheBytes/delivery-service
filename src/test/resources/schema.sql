-- MySQL Script generated by MySQL Workbench
-- Thu Apr  1 20:57:45 2021
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

-- SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
-- SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
-- SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema stacklunch
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema stacklunch
-- -----------------------------------------------------
-- CREATE SCHEMA IF NOT EXISTS `stacklunch` ;
-- DEFAULT CHARACTER SET utf8 ;
-- USE `stacklunch` ;

-- -----------------------------------------------------
-- Table `role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `role` (
  `role_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`role_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `user` (
  `user_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `user_role` INT UNSIGNED NOT NULL,
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(120) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `phone` VARCHAR(45) NOT NULL,
  `first_name` VARCHAR(45) NOT NULL,
  `last_name` VARCHAR(45) NOT NULL,
  `active` SMALLINT NOT NULL,
  PRIMARY KEY (`user_id`),
  CONSTRAINT `fk_user_role_id`
    FOREIGN KEY (`user_role`)
    REFERENCES `role` (`role_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `confirmation` (
  `token_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `user_id` INT UNSIGNED NOT NULL,
  `confirmation_token` varchar(256) NOT NULL,
  `created_date` datetime NOT NULL,
  `is_confirmed` tinyINT UNSIGNED NOT NULL DEFAULT '0',
  PRIMARY KEY (`token_id`),
  CONSTRAINT `fk_confirmation_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB;



-- -----------------------------------------------------
-- Table `menu`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `menu` (
  `menu_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`menu_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `hours`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hours` (
  `hours_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `monday_open` TIME NULL,
  `monday_close` TIME NULL,
  `tuesday_open` TIME NULL,
  `tuesday_close` TIME NULL,
  `wednesday_open` TIME NULL,
  `wednesday_close` TIME NULL,
  `thursday_open` TIME NULL,
  `thursday_close` TIME NULL,
  `friday_open` TIME NULL,
  `friday_close` TIME NULL,
  `saturday_open` TIME NULL,
  `saturday_close` TIME NULL,
  `sunday_open` TIME NULL,
  `sunday_close` TIME NULL,
  PRIMARY KEY (`hours_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `restaurant`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `restaurant` (
  `restaurant_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `manager_id` INT UNSIGNED NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `menu_id` INT UNSIGNED NOT NULL,
  `hours_id` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`restaurant_id`),
  CONSTRAINT `fk_restaurant_manager_id`
    FOREIGN KEY (`manager_id`)
    REFERENCES `user` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_restaurant_menu_id`
    FOREIGN KEY (`menu_id`)
    REFERENCES `menu` (`menu_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_restaurant_hours_id`
    FOREIGN KEY (`hours_id`)
    REFERENCES `hours` (`hours_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `genre`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `genre` (
  `genre_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`genre_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `restaurant_genre`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `restaurant_genre` (
  `restaurant_id` INT UNSIGNED NOT NULL,
  `genre_id` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`genre_id`, `restaurant_id`),
  CONSTRAINT `fk_restgenre_restaurant_id`
    FOREIGN KEY (`restaurant_id`)
    REFERENCES `restaurant` (`restaurant_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_restgenre_genre_id`
    FOREIGN KEY (`genre_id`)
    REFERENCES `genre` (`genre_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `location`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `location` (
  `location_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `street` VARCHAR(45) NOT NULL,
  `street_addition` VARCHAR(45) NOT NULL,
  `unit` VARCHAR(45) NOT NULL,
  `city` VARCHAR(45) NOT NULL,
  `state` VARCHAR(45) NOT NULL,
  `zip_code` INT NOT NULL,
  PRIMARY KEY (`location_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `user_location`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `user_location` (
  `user_id` INT UNSIGNED NOT NULL,
  `location_id` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`user_id`, `location_id`),
  CONSTRAINT `fk_userloc_user_id`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_userloc_location_id`
    FOREIGN KEY (`location_id`)
    REFERENCES `location` (`location_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `restaurant_location`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `restaurant_location` (
  `restaurant_id` INT UNSIGNED NOT NULL,
  `location_id` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`restaurant_id`, `location_id`),
  CONSTRAINT `fk_restloc_restaurant_id`
    FOREIGN KEY (`restaurant_id`)
    REFERENCES `restaurant` (`restaurant_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_restloc_location_id`
    FOREIGN KEY (`location_id`)
    REFERENCES `location` (`location_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `section`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `section` (
  `section_id` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`section_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `food`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `food` (
  `food_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `price` FLOAT UNSIGNED NOT NULL,
  `description` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`food_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `section_food`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `section_food` (
  `section_id` INT UNSIGNED NOT NULL,
  `food_id` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`section_id`, `food_id`),
  CONSTRAINT `fk_secfood_menu_id`
    FOREIGN KEY (`section_id`)
    REFERENCES `section` (`section_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_secfood_food_id`
    FOREIGN KEY (`food_id`)
    REFERENCES `food` (`food_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `menu_section`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `menu_section` (
  `menu_id` INT UNSIGNED NOT NULL,
  `section_id` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`menu_id`, `section_id`),
  CONSTRAINT `fk_menusec_menu_id`
    FOREIGN KEY (`menu_id`)
    REFERENCES `menu` (`menu_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_menusec_section_id`
    FOREIGN KEY (`section_id`)
    REFERENCES `section` (`section_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `transaction`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `transaction` (
  `transaction_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `payment_id` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`transaction_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `order`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `order` (
  `order_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `resturant_id` INT UNSIGNED NOT NULL,
  `transaction_id` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`order_id`),
  CONSTRAINT `fk_order_transaction_id`
    FOREIGN KEY (`transaction_id`)
    REFERENCES `transaction` (`transaction_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `user_order`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `user_order` (
  `user_id` INT UNSIGNED NOT NULL,
  `order_id` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`user_id`, `order_id`),
  CONSTRAINT `fk_userorder_user_id`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_userorder_order_id`
    FOREIGN KEY (`order_id`)
    REFERENCES `order` (`order_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `order_food`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `order_food` (
  `order_id` INT UNSIGNED NOT NULL,
  `food_id` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`order_id`, `food_id`),
  CONSTRAINT `fk_orderfood_order_id`
    FOREIGN KEY (`order_id`)
    REFERENCES `order` (`order_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_orderfood_food_id`
    FOREIGN KEY (`food_id`)
    REFERENCES `food` (`food_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `driver`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `driver` (
  `driver_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `user_id` INT UNSIGNED NOT NULL,
  `vehicle_id` INT UNSIGNED NOT NULL,
  `financial_id` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`driver_id`),
  CONSTRAINT `fk_driver_user_id`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- SET SQL_MODE=@OLD_SQL_MODE;
-- SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
-- SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
