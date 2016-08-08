package practice;
class Demo1 {

	int i, j;

	void f1(int i) {
		i = i + 1;
		j = i + 1;
		System.out.println(i);
		System.out.println(j);
	}
	



	public static void main(String[] args) {

		Demo1 d1 = new Demo1();
		d1.f1(6);
		System.out.println(d1.i);
		System.out.println(d1.j);

	}

}
