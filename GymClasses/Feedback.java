/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gymmanagementsystem.GymClasses;

/**
 *
 * @author Ralru
 */
public class Feedback {
    private int id;
    private Member member;
    private Trainer trainer;
    private String feedbackMessage;

    public Feedback(int id, Member member, Trainer trainer, String feedbackMessage) {
        this.id = id;
        this.member = member;
        this.trainer = trainer;
        this.feedbackMessage = feedbackMessage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Trainer getTrainer() {
        return trainer;
    }

    public void setTrainer(Trainer trainer) {
        this.trainer = trainer;
    }

    public String getFeedbackMessage() {
        return feedbackMessage;
    }

    public void setFeedbackMessage(String feedbackMessage) {
        this.feedbackMessage = feedbackMessage;
    }
    
    
}
