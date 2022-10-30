package com.cgvsu.delete;

import com.cgvsu.model.Model;
import com.cgvsu.objreader.ObjReader;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;

class DeleteTest {

    @Test
    public void testDeleteVertexes() throws IOException, DeleteException {
        Path fileName = Path.of("../ObjModels/Faceform/tmp.obj");
        String fileContent = Files.readString(fileName);
        Model model = ObjReader.read(fileContent);

        List<Integer> list = new ArrayList<>();
        list.add(0);
        Delete.deleteVertexes(model, list);

        assertEquals(8, Delete.vertexesCount(model));
        assertEquals(9, model.polygons.size());

        model = ObjReader.read(fileContent);
        list.clear();
        list.addAll(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8));
        Delete.deleteVertexes(model, list);

        assertEquals(0, Delete.vertexesCount(model));
        assertEquals(0,  model.polygons.size());

        model = ObjReader.read(fileContent);
        list.clear();
        list.add(4);
        Delete.deleteVertexes(model, list);

        assertEquals(8, Delete.vertexesCount(model));
        assertEquals(4, model.polygons.size());
    }

    @Test
    public void testVertexesCount() throws IOException {

        Path fileName = Path.of("../ObjModels/Faceform/tmp.obj");
        String fileContent = Files.readString(fileName);
        Model model = ObjReader.read(fileContent);

        assertEquals(9, Delete.vertexesCount(model) );
    }
}