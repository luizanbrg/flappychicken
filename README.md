# Flappy Chicken

Bienvenue dans la documentation de **Flappy Chicken**, une réinterprétation simple et amusante du jeu **Flappy Bird**, développée en Java à l'aide de **Swing**. Ce projet a été réalisé dans un esprit d'apprentissage, avec une inspiration issue de tutoriels en ligne et une aide limitée de GitHub Copilot pour relever le défi de mieux comprendre la logique et les concepts sous-jacents.
Ah, et j'ai choisi une poulet car je les adore, elle sont marrantes. 🎮🐔

## 🎯 Objectif

Créer un jeu où le joueur contrôle une poule qui doit sauter pour éviter des tuyaux qui défilent horizontalement, tout en accumulant des points. Si la poule entre en collision avec un tuyau ou tombe hors de l'écran, la partie est terminée.

---

## 🛠️ Installation et Pré-requis

### Prérequis
- **Java JDK** installé (version 8 ou supérieure).
- Un IDE ou éditeur de texte comme **IntelliJ IDEA**, **Eclipse**, ou **VS Code**.
- Une bibliothèque de gestion des images. Assurez-vous que les fichiers d'images suivants sont dans un dossier `resources` à la racine du projet :
  - `bg.png` : Image de fond.
  - `chicken.png` : Sprite de la poule, mais vous pouvez mettre l'image (préférablement arrondie) sans image de fond que vous voulez 😂. J'ai même essayé avec ma tête.
  - `toppipe.png` : Sprite du tuyau supérieur.
  - `bottompipe.png` : Sprite du tuyau inférieur.

### Installation
1. Clonez ce dépôt GitHub :
   ```bash
   git clone https://github.com/<votre-utilisateur>/FlappyChicken.git
   cd FlappyChicken
   ```
2. Ouvrez le projet dans votre IDE préféré.
3. Vérifiez que les ressources sont correctement placées dans un dossier `resources`.
4. Exécutez la classe `App`.

---

## 🌟 Fonctionnalités

### 🎮 Gameplay
- Le joueur contrôle une **poule** qui doit éviter des obstacles sous la forme de tuyaux en appuyant sur la barre d’espace pour sauter.
- Les tuyaux se déplacent de droite à gauche, simulant le déplacement de la poule vers la droite.
- Si la poule touche un tuyau ou dépasse les limites de l'écran, la partie se termine.
- Le score augmente chaque fois que le joueur dépasse un ensemble de tuyaux.

### 🎨 Graphismes
- **Fond** : Une image statique pour l'arrière-plan.
- **Poule** : Un sprite pour le personnage principal.
- **Tuyaux** : Paires de tuyaux (supérieur et inférieur) générés aléatoirement avec un espace entre eux.

---

## 📂 Structure des Fichiers

```
FlappyChicken/
│
├── App.java               # Point d'entrée principal du jeu
├── FlappyChicken.java     # Contient la logique du jeu et les éléments graphiques
├── resources/             # Dossier contenant les ressources graphiques
│   ├── bg.png
│   ├── chicken.png
│   ├── toppipe.png
│   ├── bottompipe.png
└── README.md              # Documentation du projet
```

---

## 🧩 Code en Détail

### **App.java**
Ce fichier est le point d'entrée du jeu. Il initialise la fenêtre principale (`JFrame`) et y ajoute le composant principal `FlappyChicken`.

**Résumé du code :**
- Définit la taille de la fenêtre (`boardWidth` et `boardHeight`).
- Configure les propriétés de la fenêtre (non redimensionnable, centrée, titre personnalisé).
- Lance une instance de `FlappyChicken` et affiche la fenêtre.

### **FlappyChicken.java**
C'est le cœur du jeu, gérant à la fois la logique (physique, collisions, génération des tuyaux) et le rendu graphique.

#### Sections principales :
1. **Chargement des ressources**
   - Images pour le fond, la poule et les tuyaux.

2. **Définition des entités**
   - `Chicken` : Classe interne représentant la poule.
   - `Pipe` : Classe interne représentant les tuyaux.

3. **Logique du jeu**
   - **Gravité** : Appliquée à la poule pour simuler une chute.
   - **Déplacement des tuyaux** : Les tuyaux défilent vers la gauche à une vitesse constante.
   - **Saut** : Appuyer sur la barre d’espace propulse la poule vers le haut.
   - **Collision** : Détecte si la poule entre en contact avec un tuyau ou sort de l'écran.

4. **Mécaniques supplémentaires**
   - Score : Incrémenté chaque fois que la poule dépasse un ensemble de tuyaux.
   - Redémarrage : Relance une partie si le joueur appuie sur la barre d’espace après un **game over**.

5. **Graphismes**
   - Utilise `paintComponent` et `Graphics` pour dessiner le fond, la poule, les tuyaux, et le score.

---

## 🚀 Comment Jouer ?

1. Lancez le jeu en exécutant `App.java`.
2. Une fenêtre s’ouvre avec le titre *Flappy Chicken!!*.
3. **Appuyez sur la barre d’espace** pour faire sauter la petite poule.
4. Évitez les tuyaux et accumulez le plus de points possible.
5. En cas de collision ou de chute, le jeu affiche un message de **Game Over**. Appuyez de nouveau sur la barre d’espace pour redémarrer.

---

## ✨ Points Clés du Code

### **Détection de collision**
```java
boolean collision(Chicken a, Pipe b) {
    return a.x < b.x + b.width &&
           a.x + a.width > b.x &&
           a.y < b.y + b.height &&
           a.y + a.height > b.y;
}
```
Cette méthode vérifie si la poule entre en contact avec un tuyau. C'était la partie la plus difficile de tout le jeu, c'était principalement sur cette partie que j'ai dû me faire aider.

### **Génération aléatoire des tuyaux**
```java
void placePipes() {
    int randomPipeY = (int) (pipeY - pipeHeight/4 - Math.random() * (pipeHeight/2));
    int openingSpace = boardHeight / 4;

    Pipe topPipe = new Pipe(topPipeImg);
    topPipe.y = randomPipeY;
    pipes.add(topPipe);

    Pipe bottomPipe = new Pipe(bottomPipeImg);
    bottomPipe.y = topPipe.y + pipeHeight + openingSpace;
    pipes.add(bottomPipe);
}
```
Les tuyaux sont générés avec une ouverture aléatoire pour ajouter de la difficulté.

### **Gravité et Saut**
```java
@Override
public void keyPressed(KeyEvent e) {
    if (e.getKeyCode() == KeyEvent.VK_SPACE) {
        velocityY = -9; // Propulse le poulet vers le haut
    }
}
```
---

## 🔍 À Améliorer

- **Animations supplémentaires** : Ajouter une animation de battement d'ailes pour la poule.
- **Sons** : Ajouter des effets sonores pour les sauts et les collisions.
- **Niveaux de difficulté** : Augmenter progressivement la vitesse des tuyaux.
- **Interface utilisateur** : Ajouter des boutons pour redémarrer ou quitter le jeu.

---

## 🏆 Résultats d'Apprentissage

- Maîtrise des concepts de **Swing** pour le rendu graphique.
- Application de **timers** pour la gestion du gameplay.
- Détection de collision en 2D.
- Gestion d'événements clavier.

---

## 💬 Contribuer

Vous avez des idées pour améliorer **Flappy Chicken** ? N’hésitez pas à forker ce projet, proposer des issues ou créer des pull requests.

Bon jeu et amusez-vous bien avec Flappy Chicken ! 🐔✨
