package breeze.collection.mutable

import collection.mutable.BitSet
import breeze.storage.{ConfigurableDefault, DefaultArrayValue, HashStorage}


/**
 *
 * @author dlwh
 */

final class OpenAddressHashArray[@specialized Elem](var index: Array[Int],
                                 var data: Array[Elem],
                                 protected var occupied: BitSet,
                                 protected var load: Int,
                                 val size: Int,
                                 val default: ConfigurableDefault[Elem] = ConfigurableDefault.default[Elem])
                                (implicit protected val manElem: Manifest[Elem],
                                 protected val defaultArrayValue: DefaultArrayValue[Elem]) extends HashStorage[Elem] with ArrayLike[Elem] {


  def this(size: Int, default: ConfigurableDefault[Elem], initialSize: Int)(implicit manElem: Manifest[Elem], defaultArrayValue: DefaultArrayValue[Elem]) = {
    this(new Array[Int](initialSize),default.makeArray(initialSize), BitSet.empty,0, size, default)
  }

  def this(size: Int, default: ConfigurableDefault[Elem])(implicit manElem: Manifest[Elem], defaultArrayValue: DefaultArrayValue[Elem]) = {
    this(size, default, 16)
  }

  def this(size: Int)(implicit manElem: Manifest[Elem], defaultArrayValue: DefaultArrayValue[Elem]) = {
    this(size, ConfigurableDefault.default[Elem])
  }

  final def apply(x: Int): Elem = rawApply(x)

  final def update(i: Int, value: Elem) {
    super.rawUpdate(i, value)
  }

  /**
   * Only iterates "active" elements
   */
  def valuesIterator = super.activeValuesIterator

}
