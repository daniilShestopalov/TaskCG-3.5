package com.cgvsu;

import com.cgvsu.delete.Delete;
import com.cgvsu.delete.DeleteException;
import com.cgvsu.model.Model;
import com.cgvsu.model.Polygon;
import com.cgvsu.objreader.ObjReader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException, DeleteException {
        Scanner scanner = new Scanner(System.in);

        Path fileName = Path.of("../ObjModels/Faceform/tmp.obj");
        String fileContent = Files.readString(fileName);

        System.out.println("Loading model ...");
        Model model = ObjReader.read(fileContent);

        System.out.println("Vertices: " + model.vertices.size());
        System.out.println("Texture vertices: " + model.textureVertices.size());
        System.out.println("Normals: " + model.normals.size());
        System.out.println("Polygons: " + model.polygons.size());
        System.out.println("Vertexes: " + Delete.vertexesCount(model));

        System.out.println("Enter a list of vertices to remove separated by a space: ");
        String indexes = scanner.nextLine();
        List<Integer> listIndex = new ArrayList<>();
        List<String> stringIndex = List.of(indexes.split(" "));
        for (String s : stringIndex) {
            if (s.matches("[-+]?\\d+")) {
                listIndex.add(Integer.parseInt(s) - 1);
            }
        }
        Delete.deleteVertexes(model, listIndex);

        System.out.println("Vertexes: " + Delete.vertexesCount(model));
        System.out.println("Polygons: " + model.polygons.size());
    }
}