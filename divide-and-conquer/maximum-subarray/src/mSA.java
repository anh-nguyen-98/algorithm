import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Representation of maximum subarray of an array.
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class mSA {
    public int start; // start index of MSA
    public int end; // end index of MSA (inclusive)
    public int sum;  // sum of MSA
}
