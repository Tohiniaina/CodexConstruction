create database evalbtp;
\c evalbtp;

CREATE TABLE userclient  (
    id SERIAL PRIMARY KEY,
    numero varchar(10) NOT NULL UNIQUE
);
INSERT INTO userclient (numero)
VALUES
    ('0347256274'),
    ('0341234567');

CREATE TABLE useradmin (
    id SERIAL PRIMARY KEY,
    username varchar,
    email varchar,
    password varchar
);

INSERT INTO useradmin (username, email, password)
VALUES
    ('Admin', 'Admin@gmail.com', 'GWu4Ys8AWv2TtPJlUmm0lQ==');


CREATE TABLE travaux (
    id SERIAL PRIMARY KEY,
    nom VARCHAR NOT NULL,
    code VARCHAR NOT NULL UNIQUE,
    unite VARCHAR,
    pu DOUBLE PRECISION
);

INSERT INTO travaux(nom, code, unite, pu) VALUES 
    ('mur de soutenement et demi Cloture ht 1m', '001', 'm3', 190000.00),
    ('Decapage des terrains meubles', '101', 'm2', 3072.87),
    ('Dressage du plateforme', '102', 'm2', 3736.26),
    ('Fouille d ouvrage terrain ferme', '103', 'm3', 9390.93),
    ('Remblai d ouvrage', '104', 'm3', 37563.26),
    ('Travaux d implantation', '105', 'fft', 152656.00);

CREATE TABLE typeMaison(
   id SERIAL PRIMARY KEY,
   nom VARCHAR,
   description VARCHAR,
   surface double precision,
   duree INT NOT NULL
);

INSERT INTO typemaison (nom, description, surface, duree) VALUES
     ('Maison traditionnelle', E'Une maison traditionnelle comprenant\n 3 chambres\n 2 toilettes\n 1 salle de bain', 175, 100);
INSERT INTO typemaison (nom, description, surface, duree) VALUES
     ('Maison contemporaine', E'Une maison contemporaine avec\n 4 chambres\n 3 toilettes\n 2 salles de bain.', 210, 130);
INSERT INTO typemaison (nom, description, surface, duree) VALUES
     ('Maison écologique', E'Une maison ecologique avec\n 2 chambres\n 1 toilette\n 1 salle de bain\n equipee de panneaux solaires.', 130, 100);
INSERT INTO typemaison (nom, description, surface, duree) VALUES
     ('Maison préfabriquée', E'Une maison préfabriquée comprenant\n 3 chambres\n 2 toilettes\n 1 salle de bain\n livrée avec une cuisine équipée.', 165, 140);

CREATE TABLE travauxMaison(
   id SERIAL PRIMARY KEY,
   typemaison_id int references typeMaison,
   traveau_id int references travaux,
   qte DOUBLE PRECISION
);

INSERT INTO travauxmaison(typemaison_id, traveau_id, qte) VALUES
    (1, 1, 25.98),
    (1, 2, 101.36),
    (1, 3, 101.36),
    (1, 4, 24.44),
    (1, 5, 15.59),
    (1, 6, 1.00);

CREATE TABLE finition(
    id SERIAL PRIMARY KEY,
    nom VARCHAR,
    pourcentage DOUBLE PRECISION
);

INSERT INTO finition(nom, pourcentage) VALUES ('Standard', 1);
INSERT INTO finition(nom, pourcentage) VALUES ('Gold', 10);
INSERT INTO finition(nom, pourcentage) VALUES ('Premium', 20);
INSERT INTO finition(nom, pourcentage) VALUES ('VIP', 30);

CREATE SEQUENCE seq_devis;

CREATE TABLE clientdevis(
    id SERIAL PRIMARY KEY,
    ref VARCHAR(15) DEFAULT 'D'||nextval('seq_devis') UNIQUE,
    lieu VARCHAR,
    userclient_id int references UserClient,
    typemaison_id int references typeMaison,
    dateDevis DATE NOT NULL,
    debutTraveaux DATE NOT NULL,
    dateFinTraveaux DATE not null,
    finition_nom VARCHAR,
    finition_pourcentage DOUBLE PRECISION
);

INSERT INTO clientdevis(lieu, userclient_id, typemaison_id, dateDevis, debutTraveaux, dateFinTraveaux, finition_nom, finition_pourcentage) VALUES
    ('Andoharanofotsy', 1, 1, '2024-05-13', '2024-05-28', '2024-07-13', 'Standard', 1);

CREATE TABLE detailDevis(
    id SERIAL PRIMARY KEY,
    clientdevis_id int references clientdevis,
    travaux_nom VARCHAR,
    code VARCHAR,
    unite VARCHAR,
    qte DOUBLE PRECISION,
    pu DOUBLE PRECISION
);

INSERT INTO detaildevis(clientdevis_id, travaux_nom, code, unite, qte, pu) VALUES
    (1, 'mur de soutenement et demi Cloture ht 1m', '001', 'm3', 26.98, 190000.00),
    (1, 'Decapage des terrains meubles', '101', 'm2', 101.36, 3072.87),
    (1, 'Dressage du plateforme', '102', 'm2', 101.36, 3736.26),
    (1, 'Fouille d ouvrage terrain ferme', '103', 'm3', 24.44, 9390.93),
    (1, 'Remblai d ouvrage', '104', 'm3', 15.59, 37563.26),
    (1, 'Travaux d implantation', '105', 'fft', 1, 152656);

CREATE SEQUENCE seq_refpayement;

CREATE TABLE paiement(
    id SERIAL PRIMARY KEY,
    clientdevis_id int references clientdevis,
    ref_paiement VARCHAR(15) DEFAULT 'PAYE'||nextval('seq_refpayement') UNIQUE,
    date Date NOT NULL,
    montant double PRECISION NOT NULL
);

CREATE OR REPLACE VIEW v_clientdevis AS
(
SELECT
    cd.*,
    COALESCE(SUM((dD.pu * dD.qte)+((dD.pu * dD.qte)*cd.finition_pourcentage)/100), 0) AS montantTotal,
    COALESCE((SELECT SUM(montant) FROM paiement WHERE clientdevis_id = cd.id), 0) AS montantPaye
FROM
    clientdevis cd
LEFT JOIN
    detailDevis dD ON cd.id = dD.clientdevis_id
GROUP BY
    cd.id
);

---

SELECT
    mois.mois,
    mois.annee,
    COALESCE(SUM(montant_total), 0) AS montant_total
FROM
    (
        SELECT generate_series(1, 12) AS mois, '2024'::int AS annee
    ) mois
        LEFT JOIN (
        SELECT
            EXTRACT(MONTH FROM cd.dateDevis) AS mois,
            EXTRACT(YEAR FROM cd.dateDevis) AS annee,
            SUM(dd.qte * dd.pu) AS montant_total
        FROM
            clientdevis cd
                JOIN
            detailDevis dd ON cd.id = dd.clientdevis_id
        WHERE
                EXTRACT(YEAR FROM cd.dateDevis) = '2024'
        GROUP BY
            EXTRACT(MONTH FROM cd.dateDevis), EXTRACT(YEAR FROM cd.dateDevis)
    ) devis_montant ON mois.mois = devis_montant.mois AND mois.annee = devis_montant.annee
GROUP BY
    mois.mois, mois.annee
ORDER BY
    mois.annee, mois.mois;


--- Import

CREATE TABLE ImportMaisonTravaux (
    type_maison VARCHAR,
    description VARCHAR,
    surface VARCHAR,
    code_travaux VARCHAR,
    type_travaux VARCHAR,
    unite VARCHAR,
    prix_unitaire VARCHAR,
    quantite VARCHAR,
    duree_travaux VARCHAR
);

CREATE TABLE ImportDevis (
    client VARCHAR,
    ref_devis VARCHAR,
    type_maison VARCHAR,
    finition VARCHAR,
    taux_finition VARCHAR,
    date_devis VARCHAR,
    date_debut VARCHAR,
    lieu VARCHAR
);


CREATE TABLE ImportPaiement (
    ref_devis VARCHAR,
    ref_paiement VARCHAR,
    date_paiement VARCHAR,
    montant VARCHAR
);

/*CREATE VIEW v_clientdevisprix AS
    SELECT
        cd.id AS clientdevis_id,
        (SUM(COALESCE(dd.pu, 0) * COALESCE(dd.qte, 0)) + ((SUM(COALESCE(dd.pu, 0) * COALESCE(dd.qte, 0)) * cd.finition_pourcentage)/100.0)) AS total,
        COALESCE(SUM(p.montant), 0) AS payer
    FROM clientdevis cd
        LEFT JOIN detailDevis dd on cd.id = dd.clientdevis_id
        LEFT JOIN paiement p on cd.id = p.clientdevis_id
    GROUP BY cd.id;

CREATE VIEW v_devisstat AS
    SELECT
        EXTRACT(YEAR FROM cd.dateDevis) AS annee,
        EXTRACT(MONTH FROM cd.dateDevis) AS mois,
        SUM(vdp.total) as total
    FROM clientdevis cd
        INNER JOIN v_clientdevisprix vdp ON cd.id = vdp.clientdevis_id
    GROUP BY
        EXTRACT(YEAR FROM cd.dateDevis),
        EXTRACT(MONTH FROM cd.dateDevis);*/

CREATE OR REPLACE VIEW v_prixdetail AS
SELECT
    d.clientdevis_id,
    SUM(d.qte * d.pu) as total
FROM detailDevis d
GROUP BY d.clientdevis_id;

CREATE OR REPLACE VIEW v_totalDevis_prix AS
    SELECT cd.id,
       (v.total + (cd.finition_pourcentage * v.total / 100.0)) as total
    FROM clientdevis cd
     JOIN v_prixdetail v ON v.clientdevis_id = cd.id;

CREATE OR REPLACE VIEW v_devisstat AS
    SELECT
        EXTRACT(YEAR FROM cd.dateDevis) AS annee,
        EXTRACT(MONTH FROM cd.dateDevis) AS mois,
        SUM(v.total) as total
    FROM
        clientdevis cd
        JOIN v_totalDevis_prix v On v.id = cd.id
    GROUP BY
        EXTRACT(YEAR FROM cd.dateDevis),
        EXTRACT(MONTH FROM cd.dateDevis);





