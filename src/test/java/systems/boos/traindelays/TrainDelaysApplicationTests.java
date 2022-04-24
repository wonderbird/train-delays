package systems.boos.traindelays;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class TrainDelaysApplicationTests {

    @Autowired
    private TrainDelaysApplication trainDelaysApplication;

    @Test
    void contextLoads() {
        assertNotNull(trainDelaysApplication);
    }

}
