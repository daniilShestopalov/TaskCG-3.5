package com.cgvsu.delete;

import com.cgvsu.model.Model;
import com.cgvsu.model.Polygon;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Delete {

    public static int vertexesCount(Model model) {
        return model.vertices.size();
    }
    private static void deleteVertex(Model model, int vertexIndex) {
        deletePolygons(model, vertexIndex);
        model.vertices.remove(vertexIndex);
        model.textureVertices.remove(vertexIndex);
        model.normals.remove(vertexIndex);
    }

    public static void deleteVertexes(Model model, List<Integer> arrVertexes) throws DeleteException {
        Collections.sort(arrVertexes);
        int offset = 0;

        for (Integer index : arrVertexes) {

            if (index - offset > vertexesCount(model)) {
                throw new DeleteException();
            }

            deleteVertex(model, index - offset);
            indexOffset(model, index - offset);
            offset++;
        }
    }

    private static void deletePolygons(Model model, int vertexIndex) {
        boolean delete = false;
        List<Integer> deleteList = new ArrayList<>();
        int c = 0;
        for (Polygon polygon : model.polygons) {
            for (Integer i : polygon.getVertexIndices()) {
                if (i == vertexIndex) {
                    delete = true;
                    break;
                }
            }
            if (delete) {
                deleteList.add(c);
                delete = false;
            }
            c++;
        }

        Collections.sort(deleteList);
        int offSet = 0;
        for (Integer d : deleteList) {
            model.polygons.remove(d - offSet);
            offSet++;


        }
    }

    private static void indexOffset(Model model, int index) {
        for (Polygon polygon : model.polygons) {
            polygon.offSet(index);
        }
    }
}
