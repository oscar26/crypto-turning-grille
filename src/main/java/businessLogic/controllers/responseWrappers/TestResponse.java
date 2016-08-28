package businessLogic.controllers.responseWrappers;

/**
 * Created by oscar on 28/08/16.
 */
public class TestResponse {

    private int[] matrix;
    private String otraVaina;

    public TestResponse(int[] matrix, String otraVaina) {
        this.matrix = matrix;
        this.otraVaina = otraVaina;
    }

    public int[] getMatrix() {
        return matrix;
    }

    public void setMatrix(int[] matrix) {
        this.matrix = matrix;
    }

    public String getOtraVaina() {
        return otraVaina;
    }

    public void setOtraVaina(String otraVaina) {
        this.otraVaina = otraVaina;
    }
}
