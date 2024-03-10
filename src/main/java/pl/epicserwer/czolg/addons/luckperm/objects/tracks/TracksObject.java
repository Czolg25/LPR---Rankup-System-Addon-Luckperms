package pl.epicserwer.czolg.addons.luckperm.objects.tracks;

import pl.epicserwer.czolg.addons.luckperm.objects.NameObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TracksObject {
    private final List<String> trackNames;
    private final HashMap<NameObject, TrackObject> trackObjectHashMap;

    public TracksObject(final List<TrackObject> trackObjectList){
        this.trackNames = new ArrayList<>();
        this.trackObjectHashMap = new HashMap<>();

        for (TrackObject trackObject : trackObjectList) {
            this.trackObjectHashMap.put(trackObject.getTrackName(), trackObject);
            this.trackNames.add(trackObject.getTrackName().toString());
        }
    }

    public List<String> getTrackNames() {
        return trackNames;
    }

    public TrackObject getTrackObject(final NameObject name){
        return this.trackObjectHashMap.get(name);
    }

    @Override
    public String toString() {
        return "TracksObject{" +
                "trackObjectHashMap=" + trackObjectHashMap +
                '}';
    }


}
