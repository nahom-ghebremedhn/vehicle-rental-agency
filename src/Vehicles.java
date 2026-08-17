public class Vehicles {

    private Vehicle[] vehicles;
    private int current;

    public Vehicles() {
    this.vehicles = new Vehicle[50]; // or any default size you want
    this.current = 0;
}

public Vehicles(int size){
    vehicles = new Vehicle[size];
    current = 0;
}

    public void add(Vehicle v){
        if (current < vehicles.length){
            vehicles[current] = v;
            current++;
        }
    }

     public void addVehicle(Vehicle v){
        add(v);
    }
    
    public Vehicle getVehicle(String vin){
        for(int i = 0; i < vehicles.length; i++){
            if(vehicles[i] != null && vin.equals(vehicles[i].getVIN())){
                return vehicles[i];
            }
        }
        return null;
            
    }

    public void reset(){
        current = 0;
    }

    public boolean hasNext(){
        return current < vehicles.length && vehicles[current] != null;
    }

    public Vehicle getNext(){
        if(hasNext()){
            return vehicles[current++];
        }

            return null;
        
        

    }
}
