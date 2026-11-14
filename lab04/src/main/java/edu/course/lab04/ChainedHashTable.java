package edu.course.lab04;

import java.util.Optional;

public class ChainedHashTable {

    // Узел связного списка (цепочки)
    private static class Node {
        final IssueId key;
        ProjectTask value;
        Node next;

        Node(IssueId key, ProjectTask value, Node next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    private Node[] buckets;
    private int size;

    public ChainedHashTable() {
        this.buckets = new Node[16]; // Начальная емкость
        this.size = 0;
    }

    private int getBucketIndex(IssueId key) {
        return (key.hashCode() & 0x7FFFFFFF) % buckets.length;
    }

    public void put(IssueId key, ProjectTask value) {
        if (key == null) throw new IllegalArgumentException("Key cannot be null");

        int index = getBucketIndex(key);
        Node current = buckets[index];

        while (current != null) {
            if (current.key.equals(key)) {
                current.value = value; // Обновляем значение, если ключ найден
                return;
            }
            current = current.next;
        }

        buckets[index] = new Node(key, value, buckets[index]);
        size++;
    }

    public Optional<ProjectTask> get(IssueId key) {
        int index = getBucketIndex(key);
        Node current = buckets[index];

        while (current != null) {
            if (current.key.equals(key)) {
                return Optional.of(current.value);
            }
            current = current.next;
        }
        return Optional.empty();
    }

    public Optional<ProjectTask> remove(IssueId key) {
        int index = getBucketIndex(key);
        Node current = buckets[index];
        Node prev = null;

        while (current != null) {
            if (current.key.equals(key)) {
                if (prev == null) {
                    buckets[index] = current.next;
                } else {
                    prev.next = current.next;
                }
                size--;
                return Optional.of(current.value);
            }
            prev = current;
            current = current.next;
        }
        return Optional.empty();
    }

    public boolean contains(IssueId key) {
        return get(key).isPresent();
    }

    public int size() {
        return size;
    }
}