package com.tong.mallbackend.models;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * ClassName: ReviewEntity
 * Package: com.tong.mallbackend.model
 */
@Entity
@Table(name = "review", schema = "mall_tong")
public class ReviewEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "review_id")
    private int reviewId;
    @Basic
    @Column(name = "product_id")
    private int productId;
    @Basic
    @Column(name = "buyer_id")
    private int buyerId;
    @Basic
    @Column(name = "rating")
    private int rating;
    @Basic
    @Column(name = "comment")
    private String comment;
    @Basic
    @Column(name = "review_time")
    private Timestamp reviewTime;

    public int getReviewId() {
        return reviewId;
    }

    public void setReviewId(int reviewId) {
        this.reviewId = reviewId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(int buyerId) {
        this.buyerId = buyerId;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Timestamp getReviewTime() {
        return reviewTime;
    }

    public void setReviewTime(Timestamp reviewTime) {
        this.reviewTime = reviewTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReviewEntity that = (ReviewEntity) o;
        return reviewId == that.reviewId && productId == that.productId && buyerId == that.buyerId && rating == that.rating && Objects.equals(comment, that.comment) && Objects.equals(reviewTime, that.reviewTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reviewId, productId, buyerId, rating, comment, reviewTime);
    }
}
