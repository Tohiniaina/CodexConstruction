ALTER TABLE userclient DISABLE TRIGGER ALL;
ALTER TABLE travaux DISABLE TRIGGER ALL;
ALTER TABLE typeMaison DISABLE TRIGGER ALL;
ALTER TABLE travauxMaison DISABLE TRIGGER ALL;
ALTER TABLE finition DISABLE TRIGGER ALL;
ALTER TABLE clientdevis DISABLE TRIGGER ALL;
ALTER SEQUENCE seq_devis RESTART WITH 1;
ALTER TABLE detailDevis DISABLE TRIGGER ALL;
ALTER TABLE paiement DISABLE TRIGGER ALL;

TRUNCATE TABLE userclient;
TRUNCATE TABLE travaux;
TRUNCATE TABLE typeMaison;
TRUNCATE TABLE travauxMaison;
TRUNCATE TABLE finition;
TRUNCATE TABLE clientdevis;
TRUNCATE TABLE detailDevis;
TRUNCATE TABLE paiement;
TRUNCATE TABLE ImportMaisonTravaux;
TRUNCATE TABLE ImportDevis;
TRUNCATE TABLE ImportPaiement;

ALTER TABLE userclient ENABLE TRIGGER ALL;
ALTER TABLE travaux ENABLE TRIGGER ALL;
ALTER TABLE typeMaison ENABLE TRIGGER ALL;
ALTER TABLE travauxMaison ENABLE TRIGGER ALL;
ALTER TABLE finition ENABLE TRIGGER ALL;
ALTER TABLE clientdevis ENABLE TRIGGER ALL;
ALTER TABLE detailDevis ENABLE TRIGGER ALL;
ALTER TABLE paiement ENABLE TRIGGER ALL;