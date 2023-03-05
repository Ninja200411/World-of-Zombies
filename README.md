## Spielidee
Meine Idee war ein Spiel zu entwickeln, dass so ähnlich ist wie "Vampire Survivor". In dem Spiel soll man in Wellen gegen Zombies kämpfen. Dies Wellen werden von Zeit zu Zeit schwerer. Durch jeden Kill und durch jede abgeschlossene Welle soll man Punkte bekommen. Diese Punkte können dann in Upgrades investiert werden. Die Upgrades sollen die Waffen stärker machen, die Lebenspunkte erhöhen oder die Schadensrate erhöhen.
## Spielziel
Das Spielziel ist so lange wie möglich zu überleben.
## Steuerung
### Bewegung
Den Charakter kann man mit W nach oben, mit A nach links, mit D nach rechts und mit S nach unten bewegen. 
### Schießen
Durch Drücken der Linken Maustaste kann man schießen und mit dem Mauszeiger Zielen. PS. Bei Laptops mit Touchbildschirm kann man auch mit dem Finger Zielen und schießen. 
### Upgrades
Mit der Taste 1 kann man sein maximales Leben erhöhen, mit 2 seine maximale Bewegungsgeschwindigkeit erhöhen, mit 3 sein Schaden erhöhen und mit 4 seine Schussgeschwindigkeit erhöhen, mit 5 seine Schussgeschwindigkeit erhöhen und mit 6 sein Schussschaden erhöhen für jewels 10 Punkte.
## Spielablauf
### Start
Stats:
- Maximales Leben: 100 (für jedes Upgrade +5)
- Bewegungsgeschwindigkeit: 1 (für jedes Upgrade +0.5)
- Schaden: 1 (für jedes Upgrade +1)
- Patronengeschwindigkeit: 3 (für jedes Upgrade +0.5)
- Nachladegeschwindigkeit: 300 (für jedes Upgrade -5, maximal 10)
- Patronenschaden: 1 (für jedes Upgrade +1)
- Punkte: 0 (für jeden Kill +1, für jede Welle +10)
- Welle: 0 (für jede Welle +1)
- Kills: 0 (für jeden Kill +1)
### Kills
Die Zombies kann man entweder durch Körperkontakt schaden hinzufügen oder durch Abschießen.
### Welle
Eine Welle ist abschlossen, wenn alle Zombies tot sind. Dann wird die nächste Welle gestartet. Die Zombies werden stärker und es kommen mehr Zombies dazu.
### Schaden
Der Spieler erleidet Schaden, wenn er mit einem Zombie kollidiert.
### Spielende
Das Spiel ist vorbei, wenn der Spieler keine Leben mehr hat.
## Markante Features
### Schießen über die Maus
Ich habe von der Klasse Mausadapter die Methode mousePressed, mouseReleased und mouseDragged implementiert und überschrieben. Dann schaue ich ob die Maustaste gedrückt ist und wenn ja, dann wird die Methode shoot aufgerufen. Diese berechnet dann die Richtung in die geschossen werden soll, normalisiert ihn und erstellt dann ein neues Bullet-Objekt. Dieses wird dann in die Liste der Bullets geschrieben. bwegt sich in dir Richtung des zuvor berechnete Vektors und wird dann gelöscht, wenn es die Grenzen des Spielfeldes überschreitet oder einen Gegner berührt.
## Quellen
### Bilder
Alle Bilder sind von mir selbst gemacht.
Außer dem Hintergrund, der ist von "https://szadiart.itch.io/rogue-fantasy-catacombs?download" ("--- License for Everyone ---
Public domain and free to use. personal or commercial. Credit is not required but appreciated. You can edit, but not resell the asset pack (original or changed)")
### Code
Der Code ist von mir selbst geschrieben und baut teilweise auf ihrer Bibliothek auf. Manchmal hat mich beim Programmieren Stackoverflow("https://stackoverflow.com/") und Javabeginners("https://javabeginners.de/") unterstützt.