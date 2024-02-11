/** Represnts a list of musical tracks. The list has a maximum capacity (int),
 *  and an actual size (number of tracks in the list, an int). */
class PlayList {
    private Track[] tracks;  // Array of tracks (Track objects)   
    private int maxSize;     // Maximum number of tracks in the array
    private int size;        // Actual number of tracks in the array

    /** Constructs an empty play list with a maximum number of tracks. */ 
    public PlayList(int maxSize) {
        this.maxSize = maxSize;
        tracks = new Track[maxSize];
        size = 0;
    }

    /** Returns the maximum size of this play list. */ 
    public int getMaxSize() {
        return maxSize;
    }
    
    /** Returns the current number of tracks in this play list. */ 
    public int getSize() {
        return size;
    }

    /** Method to get a track by index */
    public Track getTrack(int index) {
        if (index >= 0 && index < size) {
            return tracks[index];
        } else {
            return null;
        }
    }
    
    /** Appends the given track to the end of this list. 
     *  If the list is full, does nothing and returns false.
     *  Otherwise, appends the track and returns true. */
    public boolean add(Track track) {
        if (size == maxSize) {
            return false;
        }
        else {
            tracks[size] = track;
            size++;
            return true;
        } 
    }

    /** Returns the data of this list, as a string. Each track appears in a separate line. */
    //// For an efficient implementation, use StringBuilder.
    public String toString() {
        StringBuilder textL = new StringBuilder();
        for (int i = 0; i < size; i++) {
         textL.append(tracks[i]);
         textL.append('\n');
        }
        return textL.toString();
    }

    /** Removes the last track from this list. If the list is empty, does nothing. */
     public void removeLast() {
        if (size != 0) {
            tracks[size - 1] = null;
            size--;
        }
    }
    
    /** Returns the total duration (in seconds) of all the tracks in this list.*/
    public int totalDuration() {
        int sum = 0;
        for (int i = 0; i < size; i++) {
            sum += tracks[i].getDuration();
        }
        return sum;    
    }

    /** Returns the index of the track with the given title in this list.
     *  If such a track is not found, returns -1. */
    public int indexOf(String title) {
      for (int i = 0; i < size; i++) {
        if (equals(title, tracks[i].getTitle()) == true) {
            return i;
        }     
      }
    String firstUpStr = firstUp(title);
    for (int i = 0; i < size; i++) {
        if (equals(firstUpStr, tracks[i].getTitle()) == true) {
            return i;
        }     
      }
      return -1;
}
       
        
    //Helper method
    private String firstUp (String str)  {
        String firstUp = "";
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(0) != ' ') {
                if (str.charAt(0) > 'Z') {
                    firstUp += (char) ((int)str.charAt(0) - 32); 
                }
                else {
                    firstUp += str.charAt(0);
                }
            }
            else if (str.charAt(i) != ' ') {
                if (str.charAt(0) > 'Z') {
                    firstUp += str.charAt(i);
                }
                else {
                    firstUp += (char) ((int)str.charAt(i) + 32);
                } 
            }    
        }
    return firstUp;
    }
         
      

    
    

    // Helper method
    private boolean equals(String str1, String str2) {
        if (str1.length() != str2.length()) {
            return false;
        }
        for (int i = 0; i < str1.length(); i++) {
          if (str1.charAt(i) != str2.charAt(i)) {
            return false;
          }     
        }
        return true;
    }

    /** Inserts the given track in index i of this list. For example, if the list is
     *  (t5, t3, t1), then just after add(1,t4) the list becomes (t5, t4, t3, t1).
     *  If the list is the empty list (), then just after add(0,t3) it becomes (t3).
     *  If i is negative or greater than the size of this list, or if the list
     *  is full, does nothing and returns false. Otherwise, inserts the track and
     *  returns true. */
    public boolean add(int i, Track track) {

        if (size == 0 ) {
            if (i == 0) {
              tracks[0] = track;
              size++;
              return true;
            }
            else {
              return false;
            }
        }
        else if (i < 0 || i > size - 1 || size == maxSize) {
            return false;
        }
        else {
            for(int j = i; j < size - 1 ; j++) {
                tracks[j] = tracks [j+1]; 
            }
            tracks[i] = track;
            size++;
            return true;
        } 
    }
     
    /** Removes the track in the given index from this list.
     *  If the list is empty, or the given index is negative or too big for this list, 
     *  does nothing and returns -1. */
    public void remove(int i) {
        if (size != 0 &&  i > 0 && i <= size - 1 ) { 
            for(int j = i; j < size; j++) {
                tracks[j] = tracks [j - 1];
            } 
            size--;  
        }
    }

    /** Removes the first track that has the given title from this list.
     *  If such a track is not found, or the list is empty, or the given index
     *  is negative or too big for this list, does nothing. */
    public void remove(String title) {
       if (indexOf(title) != -1 && size != 0) {
        for(int i = indexOf(title); i < size; i++) {
            tracks [i] = tracks[i -1]; 
        }
        size--;
       }
    }

    /** Removes the first track from this list. If the list is empty, does nothing. */
    public void removeFirst() {
        if (size != 0) {
            for(int i = 0; i < size - 1; i++) {
                tracks [i] = tracks[i + 1];
            }
            size--; 
        }
    }
    
    /** Adds all the tracks in the other list to the end of this list. 
     *  If the total size of both lists is too large, does nothing. */
    //// An elegant and terribly inefficient implementation.
     public void add(PlayList other) {
        if (size + other.size <= maxSize) {
            for (int i = size ; i < size + other.size; i++) {
                for (int j = 0; j < other.size; j++) {
                    tracks[i] = other.tracks[j];
                    size++;    
                }
            }
        }
    }

    /** Returns the index in this list of the track that has the shortest duration,
     *  starting the search in location start. For example, if the durations are 
     *  7, 1, 6, 7, 5, 8, 7, then min(2) returns 4, since this the index of the 
     *  minimum value (5) when starting the search from index 2.  
     *  If start is negative or greater than size - 1, returns -1.
     */
    private int minIndex(int start) {
        if (start < 0 || start > size - 1 ) {
            return -1;
        }
        else {
        int minDur = 0;
        int minIndx = 0;
        for (int i = start; i < size - 1; i++) {
            if (tracks[i+1].getDuration() < tracks[i].getDuration()) {
                minDur = tracks[i+1].getDuration();
                minIndx = i+1;
            }
            else {
                minDur = tracks[i].getDuration();
                minIndx = i;
            }     
        } 
        return minIndx;
    }

    }

    /** Returns the title of the shortest track in this list. 
     *  If the list is empty, returns null. */
    public String titleOfShortestTrack() {
        return tracks[minIndex(0)].getTitle();
    }

    /** Sorts this list by increasing duration order: Tracks with shorter
     *  durations will appear first. The sort is done in-place. In other words,
     *  rather than returning a new, sorted playlist, the method sorts
     *  the list on which it was called (this list). */
    public void sortedInPlace() {
        Track temp = new Track(null, null, maxSize);
        for (int i = 0; i < size; i++) {
            int min = i;
            for (int j = i; j < size; j++) {
                if (tracks[j].getDuration() < tracks[i].getDuration()) { 
                    min = tracks[j].getDuration();
                    temp = tracks[i];
                    tracks[i] = tracks[j];
                    tracks[j] = temp;
                }   
            }
            
        }
    }
   
}
