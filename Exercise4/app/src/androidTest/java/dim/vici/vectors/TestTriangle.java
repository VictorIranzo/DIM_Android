package dim.vici.vectors;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class TestTriangle {
    @Test
    public void TransformToIsosceles() {
        Triangle triangle = new Triangle(new Point(0,0), new Point(0,1), new Point(1,2));

        assertEquals(TriangleType.Scalene, triangle.getTriangleType());
    }
}
