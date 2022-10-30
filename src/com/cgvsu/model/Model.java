package com.cgvsu.model;
import com.cgvsu.math.Vector2f;
import com.cgvsu.math.Vector3f;

import java.util.*;

public class Model {

    public List<Vector3f> vertices = new ArrayList<>();
    public List<Vector2f> textureVertices = new ArrayList<>();
    public List<Vector3f> normals = new ArrayList<>();
    public List<Polygon> polygons = new ArrayList<>();

    public void copy(Model m) {
        m.polygons = List.copyOf(polygons);
        m.vertices = List.copyOf(vertices);
        m.normals = List.copyOf(normals);
        m.textureVertices = List.copyOf(textureVertices);
    }
}
