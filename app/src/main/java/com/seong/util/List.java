package com.seong.util;

public class List {
  private Node first;
  private Node last;
  private int size;

  public void add(Object obj) {
    Node node = new Node(obj);

    if (last == null)
    {
      first = node;
      last = node;
    } else
    {
      last.next = node;
      node.prev = last;
      last = node;
    }

    size++;
  }


  public Object[] toArray() {
    //    System.out.println("size : " + this.size);
    Object[] arr = new Object[this.size];

    Node cursor = this.first;
    int i = 0;

    while (cursor != null)
    {
      arr[i++] = cursor.obj;
      cursor = cursor.next;
    }

    return arr;
  }


  public void delete(int index) {
    if (index < 0 || index >= this.size) {
      return;
    }

    int count = 0;
    Node cursor = first;
    while (cursor != null) {
      if (index == count++) {
        this.size--;
        if (first == last) {
          first = last = null;
          break;
        }
        if (cursor == first) {
          first = cursor.next;
          cursor.prev = null;
        } else {
          cursor.prev.next = cursor.next;
          if (cursor.next != null) {
            cursor.next.prev = cursor.prev;
          }
        }
        if (cursor == last) {
          last = cursor.prev;
        }
        break;
      }
      cursor = cursor.next;
    }
  }












  public int size() {
    return this.size;
  }

  static class Node {
    // 다형적 변수
    // - 해당 클래스의 객체(인스턴스의 주소) 뿐만 아니라 
    //   그 하위 클래스의 객체(인스턴스의 주소)까지 저장할 수 있다.
    Object obj;
    Node next;
    Node prev;

    Node(Object obj) {
      this.obj = obj;
    }
  }
}
