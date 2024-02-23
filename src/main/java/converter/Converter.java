package converter;

import org.locationtech.proj4j.CRSFactory;
import org.locationtech.proj4j.CoordinateReferenceSystem;
import org.locationtech.proj4j.CoordinateTransform;
import org.locationtech.proj4j.CoordinateTransformFactory;
import org.locationtech.proj4j.ProjCoordinate;

public class Converter {

	public static void main(String[] args) {
        
    	double lon = 7423270.0;
        double lat = 5477675.0;
        
        CRSFactory crsFactory = new CRSFactory();
        //CoordinateReferenceSystem inputCoordSystem = crsFactory.createFromName("epsg:20007");
        //CoordinateReferenceSystem outputCoordSystem = crsFactory.createFromName("epsg:4284");
        CoordinateReferenceSystem inputCoordSystem = crsFactory.createFromName("epsg:5565");
        CoordinateReferenceSystem outputCoordSystem = crsFactory.createFromName("epsg:4326");
        
        CoordinateTransformFactory ctFactory = new CoordinateTransformFactory();
        CoordinateTransform wgsToUtm = ctFactory.createTransform(inputCoordSystem, outputCoordSystem);
        // `result` is an output parameter to `transform()`
        ProjCoordinate result = new ProjCoordinate();
        wgsToUtm.transform(new ProjCoordinate(lon, lat), result);
        
        System.out.println(result.x + " - " + decimalToDMS(result.x));
        System.out.println(result.y + " - " + decimalToDMS(result.y));
    }

    private static String decimalToDMS(double coord) {

        double mod = coord % 1;
        int intPart = (int) coord;

        String degrees = String.valueOf(intPart);

        coord = mod * 60;
        mod = coord % 1;
        intPart = (int) coord;
        if (intPart < 0)
            intPart *= -1;

        String minutes = String.valueOf(intPart);

        coord = mod * 60;
        intPart = (int) coord;
        if (intPart < 0)
            intPart *= -1;

        String seconds = String.valueOf(intPart);
        String output = Math.abs(Integer.parseInt(degrees)) + "°" + minutes + "'" + seconds + "\"";

        return output;
    }
}
