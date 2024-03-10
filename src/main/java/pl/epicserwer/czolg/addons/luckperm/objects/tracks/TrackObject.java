package pl.epicserwer.czolg.addons.luckperm.objects.tracks;

import pl.epicserwer.czolg.addons.luckperm.objects.NameObject;

import java.util.HashMap;
import java.util.List;

public class TrackObject {
    private final NameObject trackName;
    private final HashMap<NameObject,Integer> groupsHashMap;
    private final List<NameObject> groupNameList;

    public TrackObject(final NameObject trackName,final List<NameObject> groupNameList) {
        if(trackName == null) throw new IllegalArgumentException("trackName cannot be null");
        if(groupNameList == null) throw new IllegalArgumentException("group list cannot be null");
        if(groupNameList.isEmpty()) throw new IllegalArgumentException("group name list cannot be empty");

        this.trackName = trackName;
        this.groupNameList = groupNameList;
        this.groupsHashMap = new HashMap<>();

        for (int i = 0; i < groupNameList.size(); i++) {
            final NameObject groupName = groupNameList.get(i);
            this.groupsHashMap.put(groupName,i);
        }
    }

    public NameObject getTrackName() {
        return trackName;
    }

    public NameObject getNextGroup(final NameObject groupName) {
        if(this.groupNameList.size() == 1) return this.groupNameList.get(0);

        if(!this.groupsHashMap.containsKey(groupName)) return null;

        int idGroup = this.groupsHashMap.get(groupName) + 1;
        if(idGroup == this.groupNameList.size()) return null;

        return this.groupNameList.get(idGroup);
    }

    @Override
    public String toString() {
        return "TrackObject{" +
                "trackName=" + trackName +
                ", groupNameList=" + groupNameList +
                '}';
    }
}
