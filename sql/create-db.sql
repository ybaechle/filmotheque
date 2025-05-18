
drop table if exists acteurs;
drop table if exists films;
drop table if exists genres;
drop table if exists participants;
drop table if exists membres;

create table genres(  
    id serial NOT NULL PRIMARY KEY,  
    libelle varchar(100)    );
	
CREATE TABLE PARTICIPANTS (
    id SERIAL NOT NULL PRIMARY KEY,
    nom VARCHAR(255) NOT NULL,
    prenom VARCHAR(255) NOT NULL,
    is_realisateur BOOLEAN NOT NULL,
    is_acteur BOOLEAN NOT NULL
);


CREATE TABLE FILMS (
    id SERIAL NOT NULL PRIMARY KEY,
    titre VARCHAR(255) NOT NULL,
	duree INT,
    realisateur_id INT,
    annee_sortie INT CHECK (annee_sortie >= 1800),
    synopsis TEXT CHECK (CHAR_LENGTH(synopsis) > 10 OR synopsis IS NULL),
    genre_id INT,
    FOREIGN KEY (genre_id) REFERENCES GENRES(id),
    FOREIGN KEY (realisateur_id) REFERENCES PARTICIPANTS(id)
);

create table acteurs (
	participant_id INT not null,
	film_id INT not null,
	constraint pk_acteurs primary key (participant_id, film_id )
);

alter table acteurs add constraint fk_acteurs_participant_id 
foreign key (participant_id) references participants(id);

alter table acteurs add	constraint fk_acteurs_film_id 
foreign key (film_id) references films(id);


create table membres(  
    id serial NOT NULL PRIMARY KEY,  
    pseudo varchar(100) NOT NULL,
    motDePasse varchar(100),
    isAdmin boolean default false);


insert into genres ( libelle) values ('Animation');	
insert into genres ( libelle) values ( 'Aventure');
insert into genres (libelle) values ( 'Comédie');	
insert into genres (libelle) values ( 'Horreur');

INSERT INTO PARTICIPANTS (nom, prenom, is_realisateur, is_acteur) VALUES 
('Steven', 'Spielberg', TRUE, FALSE),
('David', 'Cronenberg', TRUE, FALSE),
('Dany', 'Boon', TRUE, FALSE),
('Richard', 'Attenborough', FALSE, TRUE),
('Jeff', 'Goldblum', FALSE, TRUE),
('Geena', 'Davis', FALSE, TRUE),
('Mark', 'Rylance', TRUE, TRUE),
('Ruby', 'Barnhill', TRUE, TRUE),
('Kad', 'Merad', TRUE, TRUE),
('Billy', 'Barnhill', TRUE, TRUE),
('Billy', 'Wilder', TRUE, TRUE),
('Marylin', 'Monroe', TRUE, TRUE),
('Jack', 'Lemmon', TRUE, TRUE),
('Tony', 'Curtis', TRUE, TRUE),
('Shirley', 'Mac Laine', FALSE, TRUE),
('Pierre', 'Coffin', TRUE, TRUE),
('Quentin', 'Dupieux', TRUE, FALSE);

insert into films ( titre, annee_sortie, duree, synopsis, genre_id, realisateur_id)
values ('Some like it hot', 1959, 120,
'Lorsque deux musiciens sont témoins d''un succès populaire, ils fuient l''État dans un groupe entièrement féminin, déguisés en femmes, mais d''autres complications s''ensuivent.'
	   ,3, 10);

insert into acteurs (film_id, participant_id) values (1, 11);		   
insert into acteurs (film_id, participant_id) values (1, 12);


insert into membres (pseudo, motDePasse, isAdmin)
values ('bob', 
		'{bcrypt}$2a$10$yK2qtBpyZtmnf8c8TYzBoepFg01GBiuiH3YlDJ40KZAlADCMeVyTu',
		false);
		
insert into membres (pseudo, motDePasse, isAdmin)
values ('jane', 
		'{bcrypt}$2a$10$MjPXzqzAiabxqunFyvJGAOAhvNFl3JJ25p7HFjM/6SfqhDmkvX0UW',
		true);	
