CREATE SCHEMA Election_managementDB;

USE Election_managementDB;

CREATE TABLE political_section
(id_political_section INT NOT NULL,
section VARCHAR (45),
PRIMARY KEY (id_political_section))
ENGINE = InnoDB;

CREATE TABLE political_party
(id_political_party INT NOT NULL AUTO_INCREMENT,
party_name VARCHAR (45),
id_section INT NOT NULL,
create_date DATE DEFAULT NULL, 
PRIMARY KEY (id_political_party),
FOREIGN KEY (`id_section`) REFERENCES `political_section` (`id_political_section`))
ENGINE = InnoDB;

CREATE TABLE voting_box_type
(id_voting_box_type INT NOT NULL,
box_type VARCHAR (45),
PRIMARY KEY (id_voting_box_type))
ENGINE = InnoDB;

CREATE TABLE voting_box
(id_voting_box INT NOT NULL AUTO_INCREMENT,
address VARCHAR (45),
box_type INT NOT NULL,
PRIMARY KEY (id_voting_box),
FOREIGN KEY (`box_type`) REFERENCES `voting_box_type` (`id_voting_box_type`))
ENGINE = InnoDB;

CREATE TABLE citizen
(id_citizen INT NOT NULL,
first_name VARCHAR (45),
last_name VARCHAR (45),
year_of_birth INT,
insulation BOOL,
sick_days INT,
carry_weapon BOOL,
id_voting_box INT,
PRIMARY KEY (id_citizen),
FOREIGN KEY (`id_voting_box`) REFERENCES `voting_box` (`id_voting_box`))
ENGINE = InnoDB; 

CREATE TABLE candidate
(id_candidate INT NOT NULL,
id_party INT NOT NULL,
PRIMARY KEY (id_candidate),
FOREIGN KEY (`id_candidate`) REFERENCES `citizen` (`id_citizen`) ON DELETE CASCADE,
FOREIGN KEY (`id_party`) REFERENCES `political_party` (`id_political_party`))
ENGINE = InnoDB; 

CREATE TABLE elections
(id_election INT NOT NULL AUTO_INCREMENT,
date_election DATE DEFAULT NULL,
PRIMARY KEY (id_election))
ENGINE = InnoDB;

CREATE TABLE party_results
(id_election INT NOT NULL,
id_party INT NOT NULL,
num_votes INT,
PRIMARY KEY (id_election, id_party),
FOREIGN KEY (`id_election`) REFERENCES `elections` (`id_election`))
ENGINE = InnoDB;







