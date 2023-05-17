package hanzy.stake.limbo.hanzimbo.requests;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@Accessors(chain = true)
public class GenerateRequest {
    @NotBlank
    @Size(min = 64, max = 64)
    @Pattern(regexp = "[0-9a-eA-e]+")
    private String serverSeed;
    @NotBlank
    @Size(min = 10, max = 10)
    private String clientSeed;
    @NotBlank
    @Pattern(regexp = "(LIMBO|TEST)")
    private String gameId;
    private int startNonce = 0;
    private int endNonce = 0;
    private int cursor = 0;
}
