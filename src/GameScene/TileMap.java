package GameScene;

import Model.Enemy;
import Model.GameObject;
import Model.PowerUp;
import javafx.scene.image.Image;
import xmlwise.XmlElement;
import xmlwise.XmlParseException;
import xmlwise.Xmlwise;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * Author: Gác Xanh (phamanh)
 * Date: 18/12/2015
 * Class: OOP2
 * Project: ProjectGame
 */
public class TileMap extends GameObject {

    public TileMap(GameScene scene, int level) {
        this.scene = scene;
        this.currentLevel = level;
    }

    public TileMap() {
        loadMapFromXML(null);
    }

    public int currentLevel;
    public int tileWidth;
    public int tileHeight;
    public int mapWidth;
    public int mapHeight;
    public Image tileSet;
    public int[] data;

    public List<Enemy> enemies;
    public List<PowerUp> powerUps;

    static final String[] levelBackgrounds = {"city", "cave", "hell"};
    static final int[][] numPerLayers = {
            {6, 6, 4, 2},
            {6, 5, 4, 2},
            {6, 6, 4, 0}
    };

    private void loadMapFromXML(String fileName) {
        try {
            XmlElement root = Xmlwise.loadXml("Levels/level1.tml");
            this.tileWidth = Integer.valueOf(root.getAttribute("tileWidth"));
            this.tileHeight = Integer.valueOf(root.getAttribute("tileHeight"));
            this.mapWidth = Integer.valueOf(root.getAttribute("width"));
            this.mapHeight = Integer.valueOf(root.getAttribute("height"));
            XmlElement tileSetNode = root.getFirst();
            XmlElement imageSourceNode = tileSetNode.getFirst();
            String imageURL = imageSourceNode.getAttribute("source");
            this.tileSet = new Image(new FileInputStream("Levels/" + imageURL));

            // Load data
            LinkedList<XmlElement> layerNode = root.get("Layer");
            XmlElement dataNode = layerNode.getFirst().get("data").getFirst();
            String[] dataValue = dataNode.getValue().trim().split(".");

            this.data = new int[mapWidth * mapHeight];
            for (int i = 0; i < data.length; i++) {
                String result = dataValue[i].trim();
                this.data[i] = Integer.parseInt(result);
            }

            // Load objects: enemy and player
            enemies = new LinkedList<>();
            powerUps = new LinkedList<>();
            LinkedList<XmlElement> objectGroups = root.get("objectgroup");
            for (XmlElement objectGroup : objectGroups) {
            // load objects: player, entrance, exit

            }


        } catch (XmlParseException | IOException e) {
            e.printStackTrace();
        }
    }

}
