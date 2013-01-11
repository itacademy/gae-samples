package jp.itacademy.samples.gae.controller.task;

import java.util.ConcurrentModificationException;
import java.util.List;
import java.util.logging.Logger;

import jp.itacademy.samples.gae.meta.SaleItemMeta;
import jp.itacademy.samples.gae.model.Item;
import jp.itacademy.samples.gae.model.SaleItem;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.controller.validator.Validators;
import org.slim3.datastore.Datastore;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Transaction;

public class ItemUpdateController extends Controller {

    private static final Logger logger = Logger
        .getLogger(ItemUpdateController.class.getName());

    @Override
    public Navigation run() throws Exception {

        // �ύX���ꂽ�A�C�e����ID���p�����[�^id�Ƃ��ēn����Ă��邩�m�F
        // ���̃R���g���[���̓��[�U���u���E�U����A�N�Z�X����킯�ł͂Ȃ��A
        // �v���O����������^�X�N�Ƃ��ă^�X�N�L���[�ɓo�^���Ď��s����邱�ƂɂȂ�̂ŁA
        // �p�����[�^�ɕs��������Ƃ������Ƃ́A
        // �^�X�N�Ƃ��ēo�^���鑤�̃v���O�������Ԉ���Ă���i���o�O�j�Ƃ�������
        // �]���āA�p�����[�^�̕s���ւ̑Ή��Ƃ��ẮA�x�����O���o�͂��邾���ł悢
        Validators v = new Validators(request);
        v.add("id", v.required(), v.longType());
        v.add("timestamp", v.required(), v.longType());
        if (!v.validate()) {
            logger.warning("�p�����[�^������������܂���: " + errors);
            return null;
        }

        long id = asLong("id");
        long timestamp = asLong("timestamp");

        Key key = Datastore.createKey(Item.class, id);
        Item item = Datastore.getOrNull(Item.class, key);
        if (item == null) {
            logger.warning("�p�����[�^id�ɊY������A�C�e��������܂���: " + id);
            return null;
        }

        SaleItemMeta m = SaleItemMeta.get();

        while (true) {

            // �ύX���ꂽItem�Ɗ֘A���Ă��āA
            // Item�̕ύX�����ȍ~�ɍX�V���s���Ă��Ȃ�SaleItem���擾����B
            // �������ASaleItem�G���e�B�e�B�̃��X�g�ł͂Ȃ��A���̃L�[�̃��X�g���擾���Ă���B
            // ���R�͌�q�B
            List<Key> saleItemKeys =
                Datastore
                    .query(m)
                    .filter(
                        m.itemKey.equal(key),
                        m.lastUpdateTimestamp.lessThan(timestamp))
                    .asKeyList();

            // �ꌏ���Ȃ���΂����Ń^�X�N�͏I��
            if (saleItemKeys.isEmpty()) {
                break;
            }

            // ��Ŏ擾����SaleItem�̃L�[���X�g���g���āA
            // �G���e�B�e�B�ЂƂ��A�C�e�����E���i�E�ŏI�X�V�^�C���X�^���v���X�V���Ă����B
            // ����SaleItem�ɑ΂��鏈�������̃^�X�N�Ɠ����Ɏ��s�����\�����l�����āA
            // �ЂƂ��g�����U�N�V�������ōX�V���s�Ȃ��Ă���B
            //
            // ���SaleItem�̃��X�g�ł͂Ȃ��L�[�̃��X�g���擾���Ă��闝�R�́A
            // �ǂ݂̂��g�����U�N�V�������ł�����xSaleItem�G���e�B�e�B���ЂƂ��擾���Ȃ����K�v�����邩��B
            for (Key saleItemKey : saleItemKeys) {

                Transaction tx = Datastore.beginTransaction();

                try {
                    SaleItem saleItem =
                        Datastore.get(tx, SaleItem.class, saleItemKey);

                    // �ŏ��ɃL�[�̃��X�g���擾�����u�Ԃ��炱���܂ł̊Ԃ�
                    // �ʂ̃^�X�N�ɂ����SaleItem�G���e�B�e�B���X�V����Ă���\��������̂ŁA
                    // �g�����U�N�V�������Ń^�C���X�^���v���Ċm�F���Ă���
                    if (saleItem.getLastUpdateTimestamp() >= timestamp) {
                        continue;
                    }
                    saleItem.setItemName(item.getName());
                    saleItem.setItemPrice(item.getPrice());
                    saleItem.setLastUpdateTimestamp(timestamp);
                    Datastore.put(tx, saleItem);
                    tx.commit();

                } catch (ConcurrentModificationException e) {
                    // ����A�������ɓ���SaleItem���ʂ̃^�X�N�ɂ���čX�V���ꂽ�ꍇ��
                    // ��U����SaleItem�̏�����ۗ����Ď���SaleItem��
                    continue;
                } finally {
                    if (tx.isActive()) {
                        tx.rollback();
                    }
                }
            }
        }

        return null;
    }
}
