import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import result.Result;
import top.rabbitbyte.goods.controller.CategoryController;
import top.rabbitbyte.goods.service.CategoryService;
import top.rabbitbyte.model.vo.goods.CataLogPageVo;
import top.rabbitbyte.model.vo.goods.CategoryVo;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

/**
 * @BelongsProject: pre-mall
 * @BelongsPackage: PACKAGE_NAME
 * @Author: nemo2048
 * @CreateTime: 2024-09-13  01:01
 * @Description: TODO
 * @Version: 1.0
 */


public class CategoryControllerTest {
    @InjectMocks
    private CategoryController categoryController;

    @Mock
    private CategoryService categoryService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllCataLog() {
        // 假设返回的数据
        CataLogPageVo expectedData = new CataLogPageVo();
        when(categoryService.getAllCatalog()).thenReturn(expectedData);

        // 调用方法并验证结果
        Result<CataLogPageVo> result = categoryController.getAllCataLog();
        assertEquals(Result.ok(expectedData), result);
    }

    @Test
    void testGetCurrentCataLog() {
        // 假设返回的数据
        List<CategoryVo> expectedData = Arrays.asList(new CategoryVo(), new CategoryVo());
        Integer cataLogId = 1; // 假设的分类ID
        when(categoryService.getCatgoryListById(cataLogId)).thenReturn(expectedData);

        // 调用方法并验证结果
        Result<List<CategoryVo>> result = categoryController.getCurrentCataLog(cataLogId);
        assertEquals(Result.ok(expectedData), result);
    }
}