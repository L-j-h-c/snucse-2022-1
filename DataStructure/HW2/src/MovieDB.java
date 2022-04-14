import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Genre, Title 을 관리하는 영화 데이터베이스.
 * 
 * MyLinkedList 를 사용해 각각 Genre와 Title에 따라 내부적으로 정렬된 상태를  
 * 유지하는 데이터베이스이다. 
 */
public class MovieDB {
	MyLinkedList<MyLinkedList<MovieDBItem>> allGenreList = new MyLinkedList<>();

    public MovieDB() {
        // FIXME implement this
    	
    	// HINT: MovieDBGenre 클래스를 정렬된 상태로 유지하기 위한 
    	// MyLinkedList 타입의 멤버 변수를 초기화 한다.
    }

    public void insert(MovieDBItem item) {
		// allGenreList에 각 Node로 존재하는 MovieList<MovieDBItem>의 first.getGenre가
		// item.getGernre와 같은 것이 있는지 먼저 검사한다.
		// 있다면그 MovieList<MovieDBItem>에 add(MovieDBItem)을 해준다.
		// add가 원래 마지막에 추가하는 거지만, 그냥 순서까지 정렬해주는 친구로 만들자.

		int index = -1;
		// 아무것도 없는 경우 최초의 리스트 추가
		if (allGenreList.size()==0) {
			MyLinkedList<MovieDBItem> firstMovieList = new MyLinkedList<>();
			firstMovieList.add(item);
			allGenreList.add(firstMovieList);
			System.out.println("1");
		}

		// 하나라도 있는 경우
		else
		{
			// 장르에 대해 판단하기
			int genreIndex = 0;
			for(MyLinkedList<MovieDBItem> list : allGenreList) {
				System.out.println("장르리스트 크기 : "+allGenreList.size() +" 리스트 크기 : "+list.size());
				int compareCount = list.first().getGenre().compareTo(item.getGenre());
				System.out.println("CompareCount:" + compareCount);

				// 리스트에 있는 것이 더 크다 == 맨 앞에있는것보다 String이 작다는 것. 맨 앞에 추가해주기.
				// 동시에 같은 장르가 없다는 뜻.
				if(compareCount>0) {
					MyLinkedList<MovieDBItem> newGenreList = new MyLinkedList<>();
					newGenreList.add(item);
					allGenreList.insert(genreIndex, newGenreList);
					System.out.println("2");
					return;
				} else if(compareCount==0) {

					int movieIndex = 0;
					for(MovieDBItem dbItem : list) {
						int movieCompareCount = dbItem.compareTo(item);

						// 타이틀 비교하기
						if(movieCompareCount>0) {
							list.insert(movieIndex, item);
							System.out.println("3");
							return;
						} else if (movieCompareCount==0) {
							System.out.println("4");
							return;
						} else {
							movieIndex++;
							System.out.println("5 : "+ movieIndex);
							if(list.size()==movieIndex) {
								System.out.println("success?");
								list.insert(movieIndex, item);
								return;
							}
						}
					}
				} else {
					System.out.println("장르리스트 크기 : "+allGenreList.size() +" 리스트 크기 : "+list.size() +" " +genreIndex);
					genreIndex++;
					if(allGenreList.size()==genreIndex) {
						MyLinkedList<MovieDBItem> newGenreList = new MyLinkedList<>();
						newGenreList.add(item);
						allGenreList.insert(genreIndex, newGenreList);
						return;
					}
				}
			}
		}

        // FIXME implement this
        // Insert the given item to the MovieDB.

    	// Printing functionality is provided for the sake of debugging.
        // This code should be removed before submitting your work.
        System.err.printf("[trace] MovieDB: INSERT [%s] [%s]\n", item.getGenre(), item.getTitle());
    }

    public void delete(MovieDBItem item) {
        // FIXME implement this
        // Remove the given item from the MovieDB.
    	
    	// Printing functionality is provided for the sake of debugging.
        // This code should be removed before submitting your work.


        System.err.printf("[trace] MovieDB: DELETE [%s] [%s]\n", item.getGenre(), item.getTitle());
    }

    public MyLinkedList<MovieDBItem> search(String term) {
        // FIXME implement this
        // Search the given term from the MovieDB.
        // You should return a linked list of MovieDBItem.
        // The search command is handled at SearchCmd class.
    	
    	// Printing search results is the responsibility of SearchCmd class. 
    	// So you must not use System.out in this method to achieve specs of the assignment.
    	
        // This tracing functionality is provided for the sake of debugging.
        // This code should be removed before submitting your work.
    	System.err.printf("[trace] MovieDB: SEARCH [%s]\n", term);
    	
    	// FIXME remove this code and return an appropriate MyLinkedList<MovieDBItem> instance.
    	// This code is supplied for avoiding compilation error.   
        MyLinkedList<MovieDBItem> results = new MyLinkedList<MovieDBItem>();

        return results;
    }
    
    public MyLinkedList<MovieDBItem> items() {
        // FIXME implement this
        // Search the given term from the MovieDatabase.
        // You should return a linked list of QueryResult.
        // The print command is handled at PrintCmd class.

    	// Printing movie items is the responsibility of PrintCmd class. 
    	// So you must not use System.out in this method to achieve specs of the assignment.

    	// Printing functionality is provided for the sake of debugging.
        // This code should be removed before submitting your work.
        System.err.printf("[trace] MovieDB: ITEMS\n");

    	// FIXME remove this code and return an appropriate MyLinkedList<MovieDBItem> instance.
    	// This code is supplied for avoiding compilation error.   
        MyLinkedList<MovieDBItem> results = new MyLinkedList<MovieDBItem>();
		for(MyLinkedList<MovieDBItem> list : allGenreList) {
			for(MovieDBItem dbItem : list) {
				results.add(dbItem);
			}
		}
    	return results;
    }
}

class Genre extends Node<String> implements Comparable<Genre> {
	public Genre(String name) {
		super(name);
		throw new UnsupportedOperationException("not implemented yet");
	}
	
	@Override
	public int compareTo(Genre o) {
		return super.getItem().compareTo(o.getItem());
		// 스스로가 크면 1 같으면 0 작으면 -1
	}

	@Override
	public int hashCode() {
		throw new UnsupportedOperationException("not implemented yet");
	}

	@Override
	public boolean equals(Object obj) {
		throw new UnsupportedOperationException("not implemented yet");
	}
}

class MyLinkedList<T> implements ListInterface<T> {
	// dummy head
	Node<T> head;
	int numItems;

	public MyLinkedList() {
		head = new Node<T>(null);
	}

	/**
	 * {@code Iterable<T>}를 구현하여 iterator() 메소드를 제공하는 클래스의 인스턴스는
	 * 다음과 같은 자바 for-each 문법의 혜택을 볼 수 있다.
	 *
	 * <pre>
	 *  for (T item: iterable) {
	 *  	item.someMethod();
	 *  }
	 * </pre>
	 *
	 * @see PrintCmd#apply(MovieDB)
	 * @see SearchCmd#apply(MovieDB)
	 * @see java.lang.Iterable#iterator()
	 */
	public final Iterator<T> iterator() {
		return new MyLinkedListIterator<T>(this);
	}

	@Override
	public boolean isEmpty() {
		return head.getNext() == null;
	}

	@Override
	public int size() {
		return numItems;
	}

	@Override
	public T first() {
		return head.getNext().getItem();
	}

	@Override
	public void add(T item) {
		Node<T> last = head;
		while (last.getNext() != null) {
			last = last.getNext();
		}
		last.insertNext(item);
		numItems += 1;
	}

	// i는 index이다. index i에 item을 넣겠다는 것.
	// i가 2이면 3번쨰 item으로 넣겠다는 뜻
	public void insert(int i, T item) {
		if(i>this.size()) {
			throw new NullPointerException();
		}
		Node<T> last = head;
		for(int j=0; j<i; j++) {
			last = last.getNext();
		}
		Node<T> tmp = last.getNext();
		last.insertNext(item);
		last.getNext().setNext(tmp);
		numItems += 1;
	}

	// 아래에 add 메서드를 구현하려고 보니까, 각 Node 자체가 비교가 가능하면 좋지 않을까 하는 생각이 들었다.
	// last.getItem을 하면 T인 MovieDBItem에 접근이 가능한데.
	// MovieDBItem의 getGenre와 item의 getGenre와 같은지 검사한다.
	//
	public void insertMovieDBItem(MovieDBItem item) {
		Node<MovieDBItem> last = (Node<MovieDBItem>) head;

		// 같은거 있는지 먼저 확인, 같은거 있으면 return
		while (!last.getItem().equals(item)&&last.getNext()!=null) {
			if(last.getItem().equals(item)) return ;
			last = last.getNext();
			if(last.getNext()==null) last = (Node<MovieDBItem>) head;
		}

		int compareCount;
		int nextCompareCount;
		// 같은거 없는 경우, 넣을 자리를 찾는다.
		while (last.getNext() != null) {
			compareCount = item.compareTo(last.getItem());
			if(compareCount==-1) {
				nextCompareCount = item.compareTo(last.getNext().getItem());
				if(nextCompareCount==1) {
					last.insertNext(item);
					numItems += 1;
					return;
				}
				last = last.getNext();
			} else {
				assert false : "여기 오면 오류다";
			}
		}
		last.insertNext(item);
		numItems += 1;
	}

	@Override
	public void removeAll() {
		head.setNext(null);
	}
}

class MyLinkedListIterator<T> implements Iterator<T> {
	// FIXME implement this
	// Implement the iterator for MyLinkedList.
	// You have to maintain the current position of the iterator.
	private MyLinkedList<T> list;
	private Node<T> curr;
	private Node<T> prev;

	public MyLinkedListIterator(MyLinkedList<T> list) {
		this.list = list;
		this.curr = list.head;
		this.prev = null;
	}

	@Override
	public boolean hasNext() {
		return curr.getNext() != null;
	}

	@Override
	public T next() {
		if (!hasNext())
			throw new NoSuchElementException();

		prev = curr;
		curr = curr.getNext();

		return curr.getItem();
	}

	@Override
	public void remove() {
		if (prev == null)
			throw new IllegalStateException("next() should be called first");
		if (curr == null)
			throw new NoSuchElementException();
		Node<T> tmp = prev.getNext().getNext();
		prev.removeNext();
		prev.setNext(tmp);
		list.numItems -= 1;
		curr = prev;
		prev = null;
	}
}