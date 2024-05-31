# ECF - 30/05 - Projet de gestion de clientèle et d'inventaire

---
Cette application a pour but de gérer l'ajout, la modification et la suppression de clients et de produits,  
ainsi que l'enregistrement d'achat afin de gérer le stock

L'utilisation de l'application se fait par console, en navigant dans les menu en entrant les nombres correspondants.

### Gestion de l'inventaire
Actuellement, l'affichage, la création et la suppression des produits sont implémentés.  

```text
- - - - Gestion des produits - - - -
1. Afficher les produits
2. Ajouter un produit
3. Supprimer un produit
------------------------------------
[0]. Menu précédent
```
```text
[1] - Jean bleu - CHILD - taille M - 20.0€ - 47 en stock
[2] - Doudoune verte - MAN - taille L - 25.0€ - 20 en stock
[3] - T-shirt jaune - WOMAN - taille S - 10.0€ - 22 en stock
[5] - Manteau blanc - WOMAN - taille L - 50.0€ - 19 en stock
[6] - Jean jaune - CHILD - taille L - 12.0€ - 6 en stock
```
```text
- - - - Ajouter un produit - - - -
Description :
Un nouveau produit
Catégorie: 
[1] Enfant | [2] Homme | [3] Femme
2
Taille: 
[1] XS | [2] S | [3] M | [4] L | [5] XL | [6] XXL 
6
XXL
Prix : 
29.99
Quantité : 
5
[8] - Un nouveau produit - MAN - taille XXL - 29.99€ - 5 en stock
```

### Gestion des clients
L'ajout, la suppression et la modification des informations des clients sont implémentés, ainsi que la consultation de l'historique d'achat par client
```text
- - - - Gestion des Clients - - - -
1. Consulter l'historique des clients
2. Ajouter un client
3. Modifier les informations d'un client
4. Supprimer un client
-----------------------------------
5. Enregistrer le produit d'un client
-----------------------------------
[0]. Menu précédent
```
```text
[1] Jean Dupont email : jdupont@mail.fr
[2] Georges Abitbol email : jabitbol@mail.com
[3] Jean-Baptiste Emmanuel Zorg email : jbe.zorg@mail.zog
Sélectionnez l'identifiant client: 
2
[2] Commande pour : Georges Abitbol |  statut : CONFIRMED - Créée le 31/05/2024 à 09:26
	[6] - Jean jaune - CHILD - taille L - 12.0€ - 6 en stock [Quantité : 2]
	[1] - Jean bleu - CHILD - taille M - 20.0€ - 47 en stock [Quantité : 1]
	[3] - T-shirt jaune - WOMAN - taille S - 10.0€ - 22 en stock [Quantité : 2]
 -- TOTAL : 64.0
[4] Commande pour : Georges Abitbol |  statut : IN_PROGRESS - Créée le 31/05/2024 à 10:52
	[3] - T-shirt jaune - WOMAN - taille S - 10.0€ - 22 en stock [Quantité : 1]
	[5] - Manteau blanc - WOMAN - taille L - 50.0€ - 19 en stock [Quantité : 1]
	[1] - Jean bleu - CHILD - taille M - 20.0€ - 47 en stock [Quantité : 2]
	[6] - Jean jaune - CHILD - taille L - 12.0€ - 6 en stock [Quantité : 2]
 -- TOTAL : 124.0
```

## Gestion des ventes 
L'affichage des commandes par status est implémenté
```text
- - - - Gestion des commandes - - - -
1. Afficher les commandes
2. Valider une commande
3. Annuler une commande
-------------------------------------
[0] Menu précédent
1
- - - - Afficher les commandes - - - -
1. Afficher toutes les commandes
2. Afficher les commandes en cours
3. Afficher les commandes confirmées
4. Afficher les commandes annulées
[0] Menu précédent
4
[1] Commande pour : Jean-Baptiste Emmanuel Zorg |  statut : CANCELED - Créée le 31/05/2024 à 09:01
	[4] - Slip kangourou - MAN - taille XL - 6.99€ - 20 en stock [Quantité : 2]
	[2] - Doudoune verte - MAN - taille L - 25.0€ - 20 en stock [Quantité : 1]

[3] Commande pour : Jean Dupont |  statut : CANCELED - Créée le 31/05/2024 à 10:31
	[2] - Doudoune verte - MAN - taille L - 25.0€ - 20 en stock [Quantité : 2]
	[4] - Slip kangourou - MAN - taille XL - 6.99€ - 20 en stock [Quantité : 5]
	[1] - Jean bleu - CHILD - taille M - 20.0€ - 47 en stock [Quantité : 1]
```
La modification du status des commandes est implémenté. Seule les commandes en cours peuvent être modifiées. Les commandes confirmées et annulées ne peuvent pas repasser au status "en cours"
