import java.util.*;

class Review {
    private String user;
    private int rating;
    private String comment;

    public Review(String user, int rating, String comment) {
        this.user = user;
        this.rating = rating;
        this.comment = comment;
    }

    public int getRating() {
        return rating;
    }

    public String getUser() {
        return user;
    }

    public String getComment() {
        return comment;
    }

    @Override
    public String toString() {
        return "User: " + user + ", Rating: " + rating + ", Comment: " + comment;
    }
}

class Hotel {
    private String name;
    private List<Review> reviews;

    public Hotel(String name) {
        this.name = name;
        this.reviews = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void addReview(Review review) {
        reviews.add(review);
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public double getAverageRating() {
        if (reviews.isEmpty()) return 0;
        return reviews.stream().mapToInt(Review::getRating).average().orElse(0);
    }
}

class HotelReviewSystem {
    private List<Hotel> hotels;

    public HotelReviewSystem() {
        this.hotels = new ArrayList<>();
    }

    public void addHotel(String name) {
        hotels.add(new Hotel(name));
    }

    public void addReview(String hotelName, String user, int rating, String comment) {
        for (Hotel hotel : hotels) {
            if (hotel.getName().equalsIgnoreCase(hotelName)) {
                hotel.addReview(new Review(user, rating, comment));
                return;
            }
        }
        System.out.println("Hotel not found!");
    }

    public void displayHotelsSortedByRating() {
        hotels.sort(Comparator.comparingDouble(Hotel::getAverageRating).reversed());
        for (Hotel hotel : hotels) {
            System.out.println(hotel.getName() + " - Average Rating: " + hotel.getAverageRating());
        }
    }

    public void filterReviewsByRating(int minRating) {
        for (Hotel hotel : hotels) {
            System.out.println("Hotel: " + hotel.getName());
            hotel.getReviews().stream()
                    .filter(r -> r.getRating() >= minRating)
                    .forEach(System.out::println);
        }
    }
}

public class Res{
    public static void main(String[] args) {
        HotelReviewSystem system = new HotelReviewSystem();
        
        system.addHotel("Grand Hotel");
        system.addHotel("Ocean View");
        
        system.addReview("Grand Hotel", "Alice", 5, "Excellent stay!");
        system.addReview("Grand Hotel", "Bob", 3, "Good but noisy");
        system.addReview("Ocean View", "Charlie", 4, "Nice view and service");
        
        System.out.println("\nHotels sorted by rating:");
        system.displayHotelsSortedByRating();
        
        System.out.println("\nReviews with rating 4 and above:");
        system.filterReviewsByRating(4);
    }
}
