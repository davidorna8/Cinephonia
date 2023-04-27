#USERS;

INSERT INTO user(name, surname, username, age, password, email, region) VALUES ('Admin', '', 'admin', '', 'admin', 'admin@admin.com', '');

INSERT INTO user(name, surname, username, age, password, email, region) VALUES ('David', 'Orna', 'david345', '20', 'urjcLoL123', 'de.orna.2020@alumnos.urjc.es', 'Western Europe');

INSERT INTO user(name, surname, username, age, password, email, region) VALUES ('Eva', 'Gomez', 'eva.g', '21', '%Ri8#kKl92', 'e.gomezf.2020@alumnos.urjc.es', 'Western Europe');

INSERT INTO user(name, surname, username, age, password, email, region) VALUES ('John', 'Doe', 'yondou', '56', 'JJnewof7', 'j.doe.fresh@hotmail.com', 'Asia');

#SONGS;

INSERT INTO song(name, year, minutes, seconds, author, genre, user) VALUES ('The Trouble With Love Is', '2003',
 '3','41','Kelly Clarkson', 'Pop', (SELECT id FROM user WHERE username='admin'));

INSERT INTO song(name, year, minutes, seconds, author, genre, user) VALUES ('Cornfield Chase', '2014',
 '2','6','Hans Zimmer', 'Original Soundtrack', (SELECT id FROM user WHERE username='admin'));

INSERT INTO song(name, year, minutes, seconds, author, genre, user) VALUES ('All Along the Watchtower', '1968',
 '4','1','Jimi Hendrix', 'Rock', (SELECT id FROM user WHERE username='yondou'));

INSERT INTO song(name, year, minutes, seconds, author, genre, user) VALUES ('Stayin Alive', '1977',
 '4','9','Bee Gees', 'Rock', (SELECT id FROM user WHERE username='eva.g'));

INSERT INTO song(name, year, minutes, seconds, author, genre, user) VALUES ('Mrs. Robinson', '1967',
 '3','55','Simon and Garfunkel', 'Original Soundtrack', (SELECT id FROM user WHERE username='david345'));

INSERT INTO song(name, year, minutes, seconds, author, genre, user) VALUES ('California Somnolienta', '1965',
 '3','2','The Mamas and The Papas', 'Soul', (SELECT id FROM user WHERE username='yondou'));

#COVERS;

INSERT INTO cover(imageURL, style) VALUES ('loveactually.jpg','Collage');
INSERT INTO cover(imageURL, style) VALUES ('interstellar.jpg','Landscape');
INSERT INTO cover(imageURL, style) VALUES ('littlemermaid.jpg','Photograph');
INSERT INTO cover(imageURL, style) VALUES ('forrestgump.jpg','Photograph');
INSERT INTO cover(imageURL, style) VALUES ('thegraduate.jpg','Photograph');
INSERT INTO cover(imageURL, style) VALUES ('madagascar.jpg','Animation');

#FILMS;

INSERT INTO film(name, year, director, synopsis, genre, user, cover_id) VALUES ('Love Actually','2003', 'Richard Curtis', 'This ultimate romantic comedy weaves together a spectacular number of love affairs into one amazing story. Set almost entirely in London, England during five frantic weeks before Christmas follows a web-like pattern of inter-related, losely related and unrelated stories of a dozen or more various individuals with their love lives, or lack of them.','Romance',(SELECT id FROM user WHERE username='admin'),(SELECT id FROM cover WHERE imageURL='loveactually.jpg'));

INSERT INTO film(name, year, director, synopsis, genre, user, cover_id) VALUES ('Interstellar','2014', 'Christopher Nolan', 'In the near future Earth has been devastated by drought and famine, causing a scarcity in food and extreme changes in climate. When humanity is facing extinction, a mysterious rip in the space-time continuum is discovered, giving mankind the opportunity to widen their lifespan. A group of explorers must travel beyond our solar system in search of a planet that can sustain life.','Science fiction',(SELECT id FROM user WHERE username='admin'),(SELECT id FROM cover WHERE imageURL='interstellar.jpg'));
   
INSERT INTO film(name, year, director, synopsis, genre, user, cover_id) VALUES ('The little Mermaid','2023', 'Rob Marshall', 'The mermaid Ariel, daughter of King Triton, is fascinated with humans. She falls in love with the human prince Eric after she rescues him from a shipwreck. Condemned by her father for engaging in illicit contact with the surface world, Ariel then receives an offer from the scheming sea witch Ursula - Ursula will turn her into a human for three days, but during this time she must win the kiss of true love from Eric otherwise Ursula will own her forever. Ariel agrees but to add to the difficulty Ursula also takes Ariels voice as price of the deal and then schemes to ensure that Ariel fails.','Fantasy',(SELECT id FROM user WHERE username='admin'),(SELECT id FROM cover WHERE imageURL='littlemermaid.jpg'));

INSERT INTO film(name, year, director, synopsis, genre, user, cover_id) VALUES ('Forrest Gump','1994', 'Robert Zemeckis', 'Despite Forrests (Tom Hanks) low IQ, he is not your average guy. Learning early on from his mother (Sally Field) that life is like a box of chocolates, you never know what youre gonna get, Gump, without trying, stumbles upon some exciting events. Meanwhile, as the remarkable parade of his life goes by, Forrest never forgets Jenny (Robin Wright), the girl he loved as a boy, who makes her own journey through the turbulence of the 1960s and 1970s that is far more troubled than the path Forrest happens upon.','Drama',(SELECT id FROM user WHERE username='admin'),(SELECT id FROM cover WHERE imageURL='forrestgump.jpg'));

INSERT INTO film(name, year, director, synopsis, genre, user, cover_id) VALUES ('The Graduate','1967', 'Mike Nichols', 'In the mid-1960s, Benjamin Braddock (Dustin Hoffman), a confused college graduate, is pulled in myriad directions by his wealthy family, friends, and associates just days after receiving his degree. Seduced by alcoholic and a neurotic Mrs. Robinson (Anne Bancroft), an older friend of the family and the wife of his fathers law partner, Ben carries on an affair with the married woman even as he falls for her daughter, Elaine (Katharine Ross).','Romance',(SELECT id FROM user WHERE username='david345'),(SELECT id FROM cover WHERE imageURL='thegraduate.jpg'));

INSERT INTO film(name, year, director, synopsis, genre, user, cover_id) VALUES ('Madagascar','2005', 'Eric Darnell', 'At New Yorks Central Park Zoo, a lion, a zebra, a giraffe, and a hippo are best friends and stars of the show. But when one of the animals goes missing from their cage, the other three break free to look for him, only to find themselves reunited ... on a ship en route to Africa. When their vessel is hijacked, however, the friends, who have all been raised in captivity, learn first-hand what life can be like in the wild.','Comedy',(SELECT id FROM user WHERE username='eva.g'),(SELECT id FROM cover WHERE imageURL='madagascar.jpg'));

#N:MRELATIONSHIP;

INSERT INTO song_films(songs_id,films_id) VALUES((SELECT id FROM song WHERE name='The Trouble With Love Is'), (SELECT id FROM film WHERE name='Love Actually'));

INSERT INTO song_films(songs_id,films_id) VALUES((SELECT id FROM song WHERE name='Cornfield Chase'), (SELECT id FROM film WHERE name='Interstellar'));

INSERT INTO song_films(songs_id,films_id) VALUES((SELECT id FROM song WHERE name='All Along the Watchtower'), (SELECT id FROM film WHERE name='Forrest Gump'));

INSERT INTO song_films(songs_id,films_id) VALUES((SELECT id FROM song WHERE name='Mrs. Robinson'), (SELECT id FROM film WHERE name='Forrest Gump'));

INSERT INTO song_films(songs_id,films_id) VALUES((SELECT id FROM song WHERE name='California Somnolienta'), (SELECT id FROM film WHERE name='Forrest Gump'));

INSERT INTO song_films(songs_id,films_id) VALUES((SELECT id FROM song WHERE name='All Along the Watchtower'), (SELECT id FROM film WHERE name='The Graduate'));

INSERT INTO song_films(songs_id,films_id) VALUES((SELECT id FROM song WHERE name='Mrs. Robinson'), (SELECT id FROM film WHERE name='The Graduate'));

INSERT INTO song_films(songs_id,films_id) VALUES((SELECT id FROM song WHERE name='Stayin Alive'), (SELECT id FROM film WHERE name='Madagascar'));
