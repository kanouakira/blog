package cn.kanouakira;

import cn.kanouakira.service.CategoryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ucar.nc2.NetcdfFile;
import ucar.nc2.Variable;

import java.util.List;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class NetcdfAllTest {

    /**
     * 测试netcdfAll
     */
    @Test
    public void testSelectCategoryPage()throws Exception{
        String NCPath="C:\\Users\\kanouakira\\Desktop\\ecfine.I2017070100.003.F2017070103.nc";
        NetcdfFile open = NetcdfFile.open(NCPath);
        Variable lonS = open.findVariable("lonS");
        float[] lons = (float[])lonS.read().copyTo1DJavaArray();
        System.out.println(lons);
        Variable vis = open.findVariable("vis");
        short[][][] visD = (short[][][])vis.read().copyToNDJavaArray();
        System.out.println();

    }
}
